package altn72.tp_fil_rouge.services;

import altn72.tp_fil_rouge.entities.AnneeAcademique;
import altn72.tp_fil_rouge.repositories.AnneeAcademiqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnneeAcademiqueService {

    @Autowired
    private AnneeAcademiqueRepository anneeAcademiqueRepository;

    public List<AnneeAcademique> getAll() {
        return anneeAcademiqueRepository.findAll();
    }

}
