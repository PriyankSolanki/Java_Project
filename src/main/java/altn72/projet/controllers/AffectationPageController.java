package altn72.projet.controllers;

import altn72.projet.repositories.ApprentiRepository;
import altn72.projet.repositories.MaitreApprentissageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui")
public class AffectationPageController {

    private final ApprentiRepository apprentiRepo;
    private final MaitreApprentissageRepository maitreRepo;

    public AffectationPageController(ApprentiRepository apprentiRepo,
                                     MaitreApprentissageRepository maitreRepo) {
        this.apprentiRepo = apprentiRepo;
        this.maitreRepo = maitreRepo;
    }

    @GetMapping("/affectation")
    public String page(Model model) {
        model.addAttribute("apprentis", apprentiRepo.findAll());
        model.addAttribute("maitres", maitreRepo.findAll());
        return "affectation";
    }
}
