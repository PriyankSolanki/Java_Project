package altn72.projet.services;

import altn72.projet.dto.ApprentiCreateRequest;
import altn72.projet.entities.*;
import altn72.projet.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ApprentiCommandService {

    private final ApprentiRepository apprentiRepo;
    private final EntrepriseRepository entrepriseRepo;
    private final MaitreApprentissageRepository maitreRepo;
    private final TuteurEnseignantRepository tuteurRepo;
    private final AnneeAcademiqueRepository anneeRepo;

    public ApprentiCommandService(ApprentiRepository apprentiRepo,
                                  EntrepriseRepository entrepriseRepo,
                                  MaitreApprentissageRepository maitreRepo,
                                  TuteurEnseignantRepository tuteurRepo,
                                  AnneeAcademiqueRepository anneeRepo) {
        this.apprentiRepo = apprentiRepo;
        this.entrepriseRepo = entrepriseRepo;
        this.maitreRepo = maitreRepo;
        this.tuteurRepo = tuteurRepo;
        this.anneeRepo = anneeRepo;
    }

    @Transactional
    public Apprenti create(ApprentiCreateRequest req) {
        Apprenti a = new Apprenti();
        a.setPrenom(req.prenom());
        a.setNom(req.nom());
        a.setEmail(req.email());
        a.setTelephone(req.telephone());
        a.setAdresse(req.adresse());
        a.setEtat(req.etat());
        a.setAnneeLevel(req.anneeLevel());
        a.setRemarques(req.remarques());
        a.setFeedbackTuteurEnseignant(req.feedbackTuteurEnseignant());

        // relations optionnelles si fournies
        if (req.entrepriseId() != null) {
            Entreprise e = entrepriseRepo.findById(req.entrepriseId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Entreprise " + req.entrepriseId() + " introuvable"));
            a.setEntreprise(e);
        }
        if (req.maitreId() != null) {
            MaitreApprentissage m = maitreRepo.findById(req.maitreId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Maître " + req.maitreId() + " introuvable"));
            a.setMaitreApprentissage(m);
        }
        if (req.tuteurId() != null) {
            TuteurEnseignant t = tuteurRepo.findById(req.tuteurId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Tuteur " + req.tuteurId() + " introuvable"));
            a.setTuteurEnseignant(t);
        }
        if (req.anneeAcademiqueId() != null) {
            AnneeAcademique an = anneeRepo.findById(req.anneeAcademiqueId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Année " + req.anneeAcademiqueId() + " introuvable"));
            a.setAnneeAcademique(an);
        }

        // NB : si tu veux gérer la cohérence inverse (ajouter dans les listes du parent),
        // tu peux le faire ici, mais ce n’est pas obligatoire pour la création.

        return apprentiRepo.save(a);
    }
}
