package altn72.projet.services;

import altn72.projet.entities.TuteurEnseignant;
import altn72.projet.repositories.TuteurEnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TuteurEnseignantService {

    @Autowired
    private TuteurEnseignantRepository tuteurEnseignantRepository;

    public List<TuteurEnseignant> getAll() {
        return tuteurEnseignantRepository.findAll();
    }

    public Optional<TuteurEnseignant> getByLogin(String login) {

        TuteurEnseignant tuteurEnseignant = tuteurEnseignantRepository.findByLogin(login).orElse(null);

        if(tuteurEnseignant == null) {
            return Optional.empty();
        }else{
            return Optional.of(tuteurEnseignant);
        }
    }
}
