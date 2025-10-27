package altn72.tp_fil_rouge.services;

import altn72.tp_fil_rouge.dto.ApprentiCreateRequest;
import altn72.tp_fil_rouge.dto.ImportCsvReport;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Set;

@Service
public class ApprentiImportService {

    private final ApprentiCommandService commandService;
    private final Validator validator; // pour appliquer @Email,@NotBlank… sur le DTO

    public ApprentiImportService(ApprentiCommandService commandService, Validator validator) {
        this.commandService = commandService;
        this.validator = validator;
    }

    public ImportCsvReport importCsv(MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Fichier CSV manquant ou vide.");
        }

        var report = new ImportCsvReport();

        try (var reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
             var parser = new CSVParser(reader, CSVFormat.DEFAULT
                     .builder()
                     .setHeader()               // lit l'entête
                     .setSkipHeaderRecord(true) // saute l'entête dans l'itération
                     .setTrim(true)
                     .build())) {

            int line = 1; // header = 1
            for (CSVRecord r : parser) {
                line++;
                report.totalRows++;

                try {
                    // lecture sécurisée (null si colonne absente ou vide)
                    String prenom  = nv(r, "prenom");
                    String nom     = nv(r, "nom");
                    String email   = nv(r, "email");
                    String tel     = nv(r, "telephone");
                    String adr     = nv(r, "adresse");
                    String etat    = nv(r, "etat");
                    String anLevel = nv(r, "anneeLevel");

                    Long entrepriseId      = nl(r, "entrepriseId");
                    Long maitreId          = nl(r, "maitreId");
                    Long tuteurId          = nl(r, "tuteurId");
                    Long anneeAcademiqueId = nl(r, "anneeAcademiqueId");

                    String remarques = nv(r, "remarques");
                    String feedback  = nv(r, "feedbackTuteurEnseignant");

                    var req = new ApprentiCreateRequest(
                            prenom, nom, email, tel, adr, etat, anLevel,
                            entrepriseId, maitreId, tuteurId, anneeAcademiqueId,
                            remarques, feedback
                    );

                    // validation programmatique du DTO (annots @NotBlank, @Email, @Size…)
                    Set<ConstraintViolation<ApprentiCreateRequest>> v = validator.validate(req);
                    if (!v.isEmpty()) {
                        String msg = v.stream()
                                .map(cv -> cv.getPropertyPath() + " : " + cv.getMessage())
                                .reduce((a,b) -> a + "; " + b).orElse("Ligne invalide");
                        report.failed++;
                        report.errors.add(new ImportCsvReport.RowError(line, msg));
                        continue;
                    }

                    // création via la logique existante
                    var created = commandService.create(req);

                    report.success++;

                    // >>> Rapport enrichi avec prenom/nom/email issus de la création
                    report.successes.add(new ImportCsvReport.RowSuccess(
                            line,
                            created.getId(),
                            // on préfère la valeur retournée par create(); fallback sur la valeur du CSV
                            (created.getEmail() != null ? created.getEmail() : email),
                            created.getPrenom(),
                            created.getNom()
                    ));

                } catch (ResponseStatusException ex) {
                    // erreurs métier (IDs relations inconnus, etc.) -> message clair
                    report.failed++;
                    report.errors.add(new ImportCsvReport.RowError(
                            line,
                            ex.getReason() != null ? ex.getReason() : ex.getMessage()
                    ));
                } catch (Exception ex) {
                    report.failed++;
                    report.errors.add(new ImportCsvReport.RowError(line, ex.getMessage()));
                }
            }
        }

        return report;
    }

    private static String nv(CSVRecord r, String h) {
        try {
            String v = r.isMapped(h) ? r.get(h) : null;
            return (v == null || v.isBlank()) ? null : v;
        } catch (IllegalArgumentException e) { return null; }
    }

    private static Long nl(CSVRecord r, String h) {
        String v = nv(r, h);
        try { return (v == null) ? null : Long.valueOf(v); }
        catch (NumberFormatException e) { return null; }
    }
}
