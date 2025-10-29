//Page qui concerce la création des nouveaux étudiants
package altn72.projet.controllers;

import altn72.projet.repositories.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui/apprentis")
public class ApprentiPageController {

    private final EntrepriseRepository entrepriseRepo;
    private final MaitreApprentissageRepository maitreRepo;
    private final TuteurEnseignantRepository tuteurRepo;
    private final AnneeAcademiqueRepository anneeRepo;

    public ApprentiPageController(EntrepriseRepository entrepriseRepo,
                                  MaitreApprentissageRepository maitreRepo,
                                  TuteurEnseignantRepository tuteurRepo,
                                  AnneeAcademiqueRepository anneeRepo) {
        this.entrepriseRepo = entrepriseRepo;
        this.maitreRepo = maitreRepo;
        this.tuteurRepo = tuteurRepo;
        this.anneeRepo = anneeRepo;
    }

    @GetMapping("/nouveau")
    public String nouveau(Model model) {
        model.addAttribute("entreprises", entrepriseRepo.findAll());
        model.addAttribute("maitres", maitreRepo.findAll());
        model.addAttribute("tuteurs", tuteurRepo.findAll());
        model.addAttribute("annees", anneeRepo.findAll());
        return "apprenti_create"; // -> templates/apprenti_create.html
    }
}
