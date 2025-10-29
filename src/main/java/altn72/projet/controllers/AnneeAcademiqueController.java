package altn72.projet.controllers;

import altn72.projet.entities.AnneeAcademique;
import altn72.projet.services.AnneeAcademiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class AnneeAcademiqueController {

    @Autowired
    private AnneeAcademiqueService anneeAcademiqueService;

    @GetMapping("/annees")
    public String listeAnnees(Model model) {
        model.addAttribute("annees", anneeAcademiqueService.getAll());
        return "annee/liste";
    }

    @PostMapping("/annees/creer")
    public String creerNouvelleAnnee(@RequestParam("annee") String annee) {
        anneeAcademiqueService.creerNouvelleAnnee(annee);
        return "redirect:/ui/dashboard/dashboard";
    }
}
