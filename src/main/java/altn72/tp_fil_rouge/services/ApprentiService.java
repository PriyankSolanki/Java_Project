package altn72.tp_fil_rouge.services;

import altn72.tp_fil_rouge.entities.Apprenti;
import altn72.tp_fil_rouge.entities.TuteurEnseignant;
import altn72.tp_fil_rouge.repositories.ApprentiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApprentiService {

    @Autowired
    private ApprentiRepository apprentiRepository;

    public List<Apprenti> getAll() {
        return apprentiRepository.findAll();
    }

    public Optional<Apprenti> getById(Integer id) {
        Apprenti apprenti = apprentiRepository.findById(Long.valueOf(id)).orElse(null);

        if(apprenti == null) {
            return Optional.empty();
        }else{
            return Optional.of(apprenti);
        }
    }
}
