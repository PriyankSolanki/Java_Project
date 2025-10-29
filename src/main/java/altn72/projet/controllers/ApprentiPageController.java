package altn72.projet.controllers;

import altn72.projet.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui/apprentis")
public class ApprentiPageController {

    @Autowired
    private MaitreApprentissageService maitreApprentissageService;
    @Autowired
    private TuteurEnseignantService tuteurEnseignantService;
    @Autowired
    private EntrepriseService entrepriseService;
    @Autowired
    private AnneeAcademiqueService anneeAcademiqueService;


    @GetMapping("/nouveau")
    public String nouveau(Model model) {
        model.addAttribute("entreprises", entrepriseService.findAll());
        model.addAttribute("maitres", maitreApprentissageService.getAll());
        model.addAttribute("tuteurs", tuteurEnseignantService.getAll());
        model.addAttribute("annees", anneeAcademiqueService.getAll());
        return "apprenti_create";
    }
}
