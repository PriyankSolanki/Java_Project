package altn72.projet.services;

import altn72.projet.entities.AnneeAcademique;
import altn72.projet.repositories.AnneeAcademiqueRepository;
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
