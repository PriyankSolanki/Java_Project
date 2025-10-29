package altn72.projet.controllers;

import altn72.projet.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui")
public class ApprentiGlobalPageController {

    @Autowired
    private ApprentiService apprentiService;
    @Autowired
    private  MaitreApprentissageService maitreApprentissageService;
    @Autowired
    private  TuteurEnseignantService tuteurEnseignantService;
    @Autowired
    private EntrepriseService entrepriseService;
    @Autowired
    private AnneeAcademiqueService anneeAcademiqueService;


    @GetMapping("/apprentis/global")
    public String page(Model model) {
        var sansMaitre = apprentiService.findByMaitreApprentissageIsNull();
        model.addAttribute("apprentis", sansMaitre);
        model.addAttribute("total", sansMaitre.size());
        model.addAttribute("maitres", maitreApprentissageService.getAll());
        model.addAttribute("tuteurs", tuteurEnseignantService.getAll());
        model.addAttribute("entreprises", entrepriseService.findAll());
        model.addAttribute("annees", anneeAcademiqueService.getAll());
        return "pages/apprenti_global";
    }
}
