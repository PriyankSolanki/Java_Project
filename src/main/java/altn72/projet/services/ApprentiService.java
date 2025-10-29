package altn72.projet.services;

import altn72.projet.entities.Apprenti;
import altn72.projet.repositories.ApprentiRepository;
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

    public List<Apprenti> search(String name, String enterprise, String promotion,String keywords) {

        if(name == null || enterprise == null || promotion == null || keywords == null || name.isEmpty() && enterprise.isEmpty() && promotion.isEmpty() && keywords.isEmpty()) {
            return apprentiRepository.findAll();
        }
        return apprentiRepository.search(name, enterprise, promotion, keywords);
    }

    public List<Apprenti> getApprentisActifs() {
        return apprentiRepository.getApprentisActifs();
    }

    public List<Apprenti> findByMaitreApprentissageIsNull() {
        return apprentiRepository.findByMaitreApprentissageIsNull();
    }
}
