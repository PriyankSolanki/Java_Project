package altn72.projet.services;

import altn72.projet.entities.AnneeAcademique;
import altn72.projet.entities.Apprenti;
import altn72.projet.repositories.AnneeAcademiqueRepository;
import altn72.projet.repositories.ApprentiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnneeAcademiqueService {

    @Autowired
    private AnneeAcademiqueRepository anneeAcademiqueRepository;

    @Autowired
    private ApprentiRepository apprentiRepository;

    public List<AnneeAcademique> getAll() {
        return anneeAcademiqueRepository.findAll();
    }

    public AnneeAcademique creerNouvelleAnnee(String annee) {

        AnneeAcademique nouvelle = new AnneeAcademique();
        nouvelle.setAnnee(annee);
        anneeAcademiqueRepository.save(nouvelle);


        List<Apprenti> apprentis = apprentiRepository.findByEtatNot("archivé");

        for (Apprenti a : apprentis) {
            String niveau = a.getAnneeLevel();

            if (niveau == null) {
                // on ignore les apprentis sans niveau défini
                continue;
            }

            switch (niveau) {
                case "I1":
                    a.setAnneeLevel("I2");
                    a.setAnneeAcademique(nouvelle);
                    break;
                case "I2":
                    a.setAnneeLevel("I3");
                    a.setAnneeAcademique(nouvelle);
                    break;
                case "I3":
                    a.setEtat("INACTIF");
                    a.setAnneeLevel("I");
                    break;
            }

            apprentiRepository.save(a);
        }


        return nouvelle;
    }
}
