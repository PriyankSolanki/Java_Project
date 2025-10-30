package altn72.projet.controllers;

import altn72.projet.entities.AnneeAcademique;
import altn72.projet.entities.Apprenti;
import altn72.projet.services.AnneeAcademiqueService;
import altn72.projet.services.ApprentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AnneeAcademiqueController {

    @Autowired
    private AnneeAcademiqueService anneeAcademiqueService;
    @Autowired
    private ApprentiService apprentiService;
    @GetMapping("/annees")
    public String listeAnnees(Model model) {
        model.addAttribute("annees", anneeAcademiqueService.getAll());
        return "annee/liste";
    }

    @PostMapping("/annees/creer")
    public String creerNouvelleAnnee(Model model) {
        try {
            var nouvelle = anneeAcademiqueService.creerNouvelleAnnee();
            List<AnneeAcademique> anneeAcademiques = anneeAcademiqueService.getAll();
            List<Apprenti> apprentisActive = apprentiService.getApprentisActifs();
            AnneeAcademique anneeAcademique = anneeAcademiques.stream()
                    .filter(AnneeAcademique::isActive)
                    .findFirst()
                    .orElse(null);

            model.addAttribute("apprentis", apprentisActive);
            model.addAttribute("anneeAcademique", anneeAcademique);
            model.addAttribute("success", "Année " + nouvelle.getAnnee() + " créée avec succès !");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }

        model.addAttribute("annees", anneeAcademiqueService.getAll());
        return "pages/Dashboard";
    }
}
