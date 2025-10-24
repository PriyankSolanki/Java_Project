package altn72.tp_fil_rouge.services;

import altn72.tp_fil_rouge.entities.TuteurEnseignant;
import altn72.tp_fil_rouge.repositories.TuteurEnseignantRepository;
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

    public Optional<TuteurEnseignant> getById(Integer id) {

        TuteurEnseignant tuteurEnseignant = tuteurEnseignantRepository.findById(Long.valueOf(id)).orElse(null);

        if(tuteurEnseignant == null) {
            return Optional.empty();
        }else{
            return Optional.of(tuteurEnseignant);
        }
    }
}
