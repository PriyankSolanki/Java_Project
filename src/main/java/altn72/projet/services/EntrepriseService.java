package altn72.projet.services;

import altn72.projet.dto.EntrepriseCreateRequest;
import altn72.projet.entities.Entreprise;
import altn72.projet.repositories.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EntrepriseService {

    @Autowired
    private EntrepriseRepository repo;

    @Transactional
    public Entreprise create(EntrepriseCreateRequest req) {
        Entreprise e = new Entreprise();
        e.setRaisonSociale(req.raisonSociale().trim());
        e.setAdresse(req.adresse());
        e.setInfosAcces(req.infosAcces());
        return repo.save(e);
    }

    public List<Entreprise> findAll() { return repo.findAll(); }
}
