package altn72.projet.services;

import altn72.projet.entities.MaitreApprentissage;
import altn72.projet.repositories.MaitreApprentissageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaitreApprentissageService {

    @Autowired
    private MaitreApprentissageRepository maitreRepo;

    public List<MaitreApprentissage> getAll() {
        return maitreRepo.findAll();
    }
}
