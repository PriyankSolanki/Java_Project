package altn72.projet.controllers;

import altn72.projet.entities.AnneeAcademique;
import altn72.projet.entities.Apprenti;
import altn72.projet.entities.TuteurEnseignant;
import altn72.projet.exceptions.ApprentiNotFoundException;
import altn72.projet.repositories.ApprentiRepository;
import altn72.projet.services.AnneeAcademiqueService;
import altn72.projet.services.ApprentiService;
import org.springframework.beans.factory.annotation.Autowired;
import altn72.projet.repositories.TuteurEnseignantRepository;
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

    @Autowired
    private ApprentiRepository apprentiRepo;
    @Autowired
    private TuteurEnseignantRepository tuteurRepo;
    @Autowired
    private AnneeAcademiqueService anneeAcademiqueService;
    @Autowired
    private ApprentiService apprentiService;


    @GetMapping("/sans-maitre")
    public String apprentisSansMaitre(Model model) {
        var items = apprentiRepo.findByMaitreApprentissageIsNull();
        model.addAttribute("apprentis", items);
        model.addAttribute("total", items.size());
        return "dashboard_sans_maitre"; // -> templates/dashboard_sans_maitre.html
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, @AuthenticationPrincipal User principal) {
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
