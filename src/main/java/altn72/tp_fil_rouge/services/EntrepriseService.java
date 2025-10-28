package altn72.tp_fil_rouge.services;

import altn72.tp_fil_rouge.dto.EntrepriseCreateRequest;
import altn72.tp_fil_rouge.entities.Entreprise;
import altn72.tp_fil_rouge.repositories.EntrepriseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EntrepriseService {
    private final EntrepriseRepository repo;

    public EntrepriseService(EntrepriseRepository repo) {
        this.repo = repo;
    }

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
