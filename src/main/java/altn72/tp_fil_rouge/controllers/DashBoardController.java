package altn72.tp_fil_rouge.controllers;

import altn72.tp_fil_rouge.entities.AnneeAcademique;
import altn72.tp_fil_rouge.entities.Apprenti;
import altn72.tp_fil_rouge.entities.TuteurEnseignant;
import altn72.tp_fil_rouge.exceptions.ApprentiNotFoundException;
import altn72.tp_fil_rouge.exceptions.TuteurEnseignantNotFoundException;
import altn72.tp_fil_rouge.services.AnneeAcademiqueService;
import altn72.tp_fil_rouge.services.ApprentiService;
import altn72.tp_fil_rouge.services.TuteurEnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class DashBoardController {

    @Autowired
    private AnneeAcademiqueService  anneeAcademiqueService;
    @Autowired
    private TuteurEnseignantService tuteurEnseignantService;
    @Autowired
    private ApprentiService apprentiService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Integer idTuteur = 1; // récupérer l'id du tuteur enseignant connecté

        TuteurEnseignant tuteurEnseignantConnected = tuteurEnseignantService.getById(idTuteur)
                .orElseThrow(() -> new TuteurEnseignantNotFoundException(idTuteur));


        List<Apprenti> apprentisTuteurEnseignant = tuteurEnseignantConnected.getApprentis();

        List<Apprenti> apprentisActive = apprentisTuteurEnseignant.stream()
                .filter(apprenti -> apprenti.getAnneeAcademique().isActive())
                .toList();

        List<AnneeAcademique> anneeAcademiques = anneeAcademiqueService.getAll();

        AnneeAcademique anneeAcademique = anneeAcademiques.stream()
                .filter(AnneeAcademique::isActive)
                .findFirst()
                .orElse(null);

        model.addAttribute("apprentis", apprentisActive);
        model.addAttribute("anneeAcademique", anneeAcademique);
        return "Dashboard";
    }

    @GetMapping("/apprentice/{id}")
    public String afficherInfosApprenti(@PathVariable Integer id, Model model) {
        Apprenti apprenti = apprentiService.getById(id).orElseThrow(
                () -> new ApprentiNotFoundException(id)
        );
        model.addAttribute("apprenti", apprenti);
        return "ApprentiInfos";
    }
}
