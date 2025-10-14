package altn72.tp_fil_rouge.controllers;

import altn72.tp_fil_rouge.entities.AnneeAcademique;
import altn72.tp_fil_rouge.entities.Apprenti;
import altn72.tp_fil_rouge.entities.TuteurEnseignant;
import altn72.tp_fil_rouge.services.AnneeAcademiqueService;
import altn72.tp_fil_rouge.services.TuteurEnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class DashBoardController {

    @Autowired
    private AnneeAcademiqueService  anneeAcademiqueService;
    @Autowired
    private TuteurEnseignantService tuteurEnseignantService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        List<Apprenti> apprentisTuteurEnseignant = new ArrayList<>();
        Optional<TuteurEnseignant> tuteurEnseignantConnected = tuteurEnseignantService.getById(1); // récupérer le tuteur enseignant connecté
        if (tuteurEnseignantConnected.isEmpty()) {
            // lever une exception
        }else{
            apprentisTuteurEnseignant = tuteurEnseignantConnected.get().getApprentis();
        }

        List<AnneeAcademique> anneeAcademiques = anneeAcademiqueService.getAll();

        AnneeAcademique anneeAcademique = anneeAcademiques.stream()
                .filter(AnneeAcademique::isActive)
                .findFirst()
                .orElse(null);

        List<Apprenti> apprentisAnneeAcademique = (anneeAcademique!=null) ? anneeAcademique.getApprentis() : new ArrayList<>();

        List<Apprenti> apprentisCommuns = apprentisTuteurEnseignant.stream()
                .filter(apprentisAnneeAcademique::contains)
                .toList();

        model.addAttribute("apprentis", apprentisCommuns);
        model.addAttribute("anneeAcademique", anneeAcademique);
        return "Dashboard";
    }


}
