// Nouveau fichier: ApprentiGlobalPageController.java
package altn72.tp_fil_rouge.controllers;

import altn72.tp_fil_rouge.repositories.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui")
public class ApprentiGlobalPageController {

    private final ApprentiRepository apprentiRepo;
    private final MaitreApprentissageRepository maitreRepo;
    private final TuteurEnseignantRepository tuteurRepo;
    private final EntrepriseRepository entrepriseRepo;
    private final AnneeAcademiqueRepository anneeRepo;

    public ApprentiGlobalPageController(ApprentiRepository apprentiRepo,
                                        MaitreApprentissageRepository maitreRepo,
                                        TuteurEnseignantRepository tuteurRepo,
                                        EntrepriseRepository entrepriseRepo,
                                        AnneeAcademiqueRepository anneeRepo) {
        this.apprentiRepo = apprentiRepo;
        this.maitreRepo = maitreRepo;
        this.tuteurRepo = tuteurRepo;
        this.entrepriseRepo = entrepriseRepo;
        this.anneeRepo = anneeRepo;
    }

    @GetMapping("/apprentis/global")
    public String page(Model model) {
        var sansMaitre = apprentiRepo.findByMaitreApprentissageIsNull();
        model.addAttribute("apprentis", sansMaitre);
        model.addAttribute("total", sansMaitre.size());
        model.addAttribute("maitres", maitreRepo.findAll());
        model.addAttribute("tuteurs", tuteurRepo.findAll());
        model.addAttribute("entreprises", entrepriseRepo.findAll());
        model.addAttribute("annees", anneeRepo.findAll());
        return "apprenti_global";
    }
}
