package altn72.projet.controllers;

import altn72.projet.services.ApprentiService;
import altn72.projet.services.MaitreApprentissageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui")
public class AffectationPageController {

    @Autowired
    private ApprentiService apprentiService;
    @Autowired
    private MaitreApprentissageService maitreApprentissageService;


    @GetMapping("/affectation")
    public String page(Model model) {
        model.addAttribute("apprentis", apprentiService.getAll());
        model.addAttribute("maitres", maitreApprentissageService.getAll());
        return "affectation";
    }
}
