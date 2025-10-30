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


    private String genererNouvelleAnnee() {
        AnneeAcademique derniere = anneeAcademiqueRepository.findTopByOrderByAnneeDesc();

        if (derniere == null) {
            throw new IllegalArgumentException("Aucune année existante. Créez la première manuellement.");
        }

        String[] parts = derniere.getAnnee().split("-");
        try {
            int debut = Integer.parseInt(parts[0].trim());
            int fin = Integer.parseInt(parts[1].trim());
            return (debut + 1) + "-" + (fin + 1);
        } catch (Exception e) {
            throw new IllegalArgumentException("Format d'année invalide : " + derniere.getAnnee());
        }
    }


    public AnneeAcademique creerNouvelleAnnee() {
        AnneeAcademique derniere = anneeAcademiqueRepository.findTopByOrderByAnneeDesc();

        if (derniere == null) {
            throw new IllegalArgumentException("Aucune année trouvée. Créez d'abord la première manuellement.");
        }

        String nouvelleAnnee = genererNouvelleAnnee();

        if (anneeAcademiqueRepository.existsByAnnee(nouvelleAnnee)) {
            throw new IllegalArgumentException("L'année " + nouvelleAnnee + " existe déjà !");
        }

        //desactive l’ancienne annee
        derniere.setActive(false);
        anneeAcademiqueRepository.save(derniere);

        // cree la nouvelle année active
        AnneeAcademique nouvelle = new AnneeAcademique();
        nouvelle.setAnnee(nouvelleAnnee);
        nouvelle.setActive(true);
        anneeAcademiqueRepository.save(nouvelle);

        List<Apprenti> apprentis = apprentiRepository.findByEtatNot("INACTIF");

        for (Apprenti a : apprentis) {
            String niveau = a.getAnneeLevel();
            if (niveau == null) continue;

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
                    break;
            }
            apprentiRepository.save(a);
        }

        return nouvelle;
    }
}
