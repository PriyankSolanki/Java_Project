package altn72.projet.services;

import altn72.projet.entities.Apprenti;
import altn72.projet.entities.MaitreApprentissage;
import altn72.projet.repositories.ApprentiRepository;
import altn72.projet.repositories.MaitreApprentissageRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AffectationApprentiMaitre {

    private final ApprentiRepository apprentiRepo;
    private final MaitreApprentissageRepository maitreRepo;

    public AffectationApprentiMaitre(ApprentiRepository apprentiRepo,
                              MaitreApprentissageRepository maitreRepo) {
        this.apprentiRepo = apprentiRepo;
        this.maitreRepo = maitreRepo;
    }

    @Transactional
    public Apprenti affecterApprentiAuMaitre(Long apprentiId, Long maitreId) {
        Apprenti apprenti = apprentiRepo.findById(apprentiId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Apprenti " + apprentiId + " introuvable"));

        MaitreApprentissage maitre = maitreRepo.findById(maitreId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Maître d’apprentissage " + maitreId + " introuvable"));

        // si déjà affecté au même maître -> rien à faire
        if (apprenti.getMaitreApprentissage() != null &&
                maitreId.equals(apprenti.getMaitreApprentissage().getId())) {
            return apprenti;
        }

        // retirer du précédent maître si nécessaire
        MaitreApprentissage ancien = apprenti.getMaitreApprentissage();
        if (ancien != null && ancien.getApprentis() != null) {
            ancien.getApprentis().remove(apprenti);
        }

        // affecter au nouveau maître
        apprenti.setMaitreApprentissage(maitre);
        if (maitre.getApprentis() != null && !maitre.getApprentis().contains(apprenti)) {
            maitre.getApprentis().add(apprenti);
        }

        return apprentiRepo.save(apprenti);
    }
}
