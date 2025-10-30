package altn72.projet.services;

import altn72.projet.dto.ApprentiCreateRequest;
import altn72.projet.entities.*;
import altn72.projet.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ApprentiCommandService {

    @Autowired
    private ApprentiRepository apprentiRepo;
    @Autowired
    private EntrepriseRepository entrepriseRepo;
    @Autowired
    private MaitreApprentissageRepository maitreRepo;
    @Autowired
    private TuteurEnseignantRepository tuteurRepo;
    @Autowired
    private AnneeAcademiqueRepository anneeRepo;
    @Autowired
    private AnneeAcademiqueRepository anneeAcademiqueRepository;


    @Transactional
    public Apprenti create(ApprentiCreateRequest req, TuteurEnseignant tuteurEnseignant) {
        Apprenti a = new Apprenti();
        a.setPrenom(req.prenom());
        a.setNom(req.nom());
        a.setEmail(req.email());
        a.setTelephone(req.telephone());
        a.setAdresse(req.adresse());
        if(req.etat() == null){
            a.setEtat("ACTIF");
        }else{
            a.setEtat(req.etat());
        }
        a.setAnneeLevel(req.anneeLevel());
        a.setRemarques(req.remarques());

        a.setFeedbackTuteurEnseignant(req.feedbackTuteurEnseignant());

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
        }else{
            a.setTuteurEnseignant(tuteurEnseignant);
        }

        if (req.anneeAcademiqueId() != null) {
            AnneeAcademique an = anneeRepo.findById(req.anneeAcademiqueId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Année " + req.anneeAcademiqueId() + " introuvable"));
            a.setAnneeAcademique(an);
        }else{
            AnneeAcademique anneeAcademique = anneeAcademiqueRepository.findFirstByActive(true);
            a.setAnneeAcademique(anneeAcademique);
        }

        return apprentiRepo.save(a);
    }
}
