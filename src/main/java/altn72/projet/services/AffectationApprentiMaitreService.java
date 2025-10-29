package altn72.projet.services;

import altn72.projet.entities.Apprenti;
import altn72.projet.entities.MaitreApprentissage;
import altn72.projet.repositories.ApprentiRepository;
import altn72.projet.repositories.MaitreApprentissageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AffectationApprentiMaitreService {

    @Autowired
    private ApprentiRepository apprentiRepo;
    @Autowired
    private MaitreApprentissageRepository maitreRepo;


    @Transactional
    public Apprenti affecterApprentiAuMaitre(Long apprentiId, Long maitreId) {
        Apprenti apprenti = apprentiRepo.findById(apprentiId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Apprenti " + apprentiId + " introuvable"));

        MaitreApprentissage maitre = maitreRepo.findById(maitreId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Maître d’apprentissage " + maitreId + " introuvable"));

        if (apprenti.getMaitreApprentissage() != null &&
                maitreId.equals(apprenti.getMaitreApprentissage().getId())) {
            return apprenti;
        }

        MaitreApprentissage ancien = apprenti.getMaitreApprentissage();
        if (ancien != null && ancien.getApprentis() != null) {
            ancien.getApprentis().remove(apprenti);
        }

        apprenti.setMaitreApprentissage(maitre);
        if (maitre.getApprentis() != null && !maitre.getApprentis().contains(apprenti)) {
            maitre.getApprentis().add(apprenti);
        }

        return apprentiRepo.save(apprenti);
    }
}
