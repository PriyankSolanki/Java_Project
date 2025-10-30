package altn72.projet.controllers;

import altn72.projet.dto.ImportCsvReport;
import altn72.projet.entities.TuteurEnseignant;
import altn72.projet.exceptions.TuteurEnseignantNotFoundException;
import altn72.projet.services.ApprentiImportService;
import altn72.projet.services.TuteurEnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/apprentis")
public class ApprentiImportController {

    @Autowired
    private  ApprentiImportService importService;
    @Autowired
    private TuteurEnseignantService tuteurEnseignantService;

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImportCsvReport> importCsv(@RequestParam("file") MultipartFile file, @AuthenticationPrincipal User principal) throws Exception {
        TuteurEnseignant tuteurEnseignant = tuteurEnseignantService.getByLogin(principal.getUsername()).orElseThrow(() -> new TuteurEnseignantNotFoundException(null));

        ImportCsvReport report = importService.importCsv(file, tuteurEnseignant);
        return ResponseEntity.ok(report);
    }
}
