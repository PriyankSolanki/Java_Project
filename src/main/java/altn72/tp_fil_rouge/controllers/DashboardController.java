package altn72.tp_fil_rouge.controllers;

import altn72.tp_fil_rouge.entities.AnneeAcademique;
import altn72.tp_fil_rouge.entities.Apprenti;
import altn72.tp_fil_rouge.entities.TuteurEnseignant;
import altn72.tp_fil_rouge.exceptions.ApprentiNotFoundException;
import altn72.tp_fil_rouge.exceptions.TuteurEnseignantNotFoundException;
import altn72.tp_fil_rouge.repositories.ApprentiRepository;
import altn72.tp_fil_rouge.services.AnneeAcademiqueService;
import altn72.tp_fil_rouge.services.ApprentiService;
import altn72.tp_fil_rouge.services.TuteurEnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import altn72.tp_fil_rouge.repositories.TuteurEnseignantRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/ui/dashboard")
public class DashboardController {

    private final ApprentiRepository apprentiRepo;
    private final TuteurEnseignantRepository tuteurRepo;

    @Autowired
    private AnneeAcademiqueService anneeAcademiqueService;
    @Autowired
    private TuteurEnseignantService tuteurEnseignantService;
    @Autowired
    private ApprentiService apprentiService;

    public DashboardController(ApprentiRepository apprentiRepo, TuteurEnseignantRepository tuteurRepo) {
        this.apprentiRepo = apprentiRepo;
        this.tuteurRepo = tuteurRepo;
    }

    @GetMapping("/sans-maitre")
    public String apprentisSansMaitre(Model model) {
        var items = apprentiRepo.findByMaitreApprentissageIsNull();
        model.addAttribute("apprentis", items);
        model.addAttribute("total", items.size());
        return "dashboard_sans_maitre"; // -> templates/dashboard_sans_maitre.html
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // récupère le TuteurEnseignant connecté via son login
        TuteurEnseignant tuteurEnseignantConnected = tuteurRepo.findByLogin(principal.getUsername())
                .orElseThrow(() -> new RuntimeException("Tuteur non trouvé"));


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
        model.addAttribute("prenom", tuteurEnseignantConnected.getPrenom());
        model.addAttribute("nom", tuteurEnseignantConnected.getNom());
        return "pages/Dashboard";
    }

    @GetMapping("/apprentice/{id}")
    public String afficherInfosApprenti(@PathVariable Integer id, Model model) {
        Apprenti apprenti = apprentiService.getById(id).orElseThrow(
                () -> new ApprentiNotFoundException(id)
        );
        model.addAttribute("apprenti", apprenti);
        return "pages/ApprentiInfos";
    }

    @GetMapping("/search")
    public String search(Model model,
                         @RequestParam(required = false) String name,
                         @RequestParam(required = false) String enterprise,
                         @RequestParam(required = false) String promotion,
                         @RequestParam(required = false) String keywords) {

        List<Apprenti> apprentis = apprentiService.search(name, enterprise, promotion, keywords);
        model.addAttribute("apprentis", apprentis);
        return "pages/Search";
    }
}
