package altn72.tp_fil_rouge.services;

import altn72.tp_fil_rouge.entities.Apprenti;
import altn72.tp_fil_rouge.repositories.ApprentiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprentiService {

    @Autowired
    private ApprentiRepository apprentiRepository;

    public List<Apprenti> getAll() {
        return apprentiRepository.findAll();
    }
}
