package altn72.tp_fil_rouge.controllers;

import altn72.tp_fil_rouge.entities.AnneeAcademique;
import altn72.tp_fil_rouge.entities.Apprenti;
import altn72.tp_fil_rouge.entities.TuteurEnseignant;
import altn72.tp_fil_rouge.exceptions.ApprentiNotFoundException;
import altn72.tp_fil_rouge.exceptions.TuteurEnseignantNotFoundException;
import altn72.tp_fil_rouge.repositories.TuteurEnseignantRepository;
import altn72.tp_fil_rouge.services.AnneeAcademiqueService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import altn72.tp_fil_rouge.services.ApprentiService;
import altn72.tp_fil_rouge.services.TuteurEnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DashBoardController {

    @Autowired
    private AnneeAcademiqueService  anneeAcademiqueService;
    @Autowired
    private TuteurEnseignantService tuteurEnseignantService;
    @Autowired
    private ApprentiService apprentiService;
    @Autowired
    private TuteurEnseignantRepository tuteurRepo;
    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal User principal,Model model) {
        // récupère le TuteurEnseignant connecté via son login
        TuteurEnseignant tuteur = tuteurRepo.findByLogin(principal.getUsername())
                .orElseThrow(() -> new RuntimeException("Tuteur non trouvé"));

        model.addAttribute("prenom", tuteur.getPrenom());
        model.addAttribute("nom", tuteur.getNom());


        Integer idTuteur = 1; // récupérer l'id du tuteur enseignant connecté

        TuteurEnseignant tuteurEnseignantConnected = tuteurEnseignantService.getById(idTuteur)
                .orElseThrow(() -> new TuteurEnseignantNotFoundException(idTuteur));


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
        return "pages/Dashboard";
    }

    @GetMapping("/apprentice/{id}")
    public String afficherInfosApprenti(@AuthenticationPrincipal User principal,@PathVariable Integer id, Model model) {
        TuteurEnseignant tuteur = tuteurRepo.findByLogin(principal.getUsername())
                .orElseThrow(() -> new RuntimeException("Tuteur non trouvé"));

        model.addAttribute("prenom", tuteur.getPrenom());
        model.addAttribute("nom", tuteur.getNom());

        Apprenti apprenti = apprentiService.getById(id).orElseThrow(
                () -> new ApprentiNotFoundException(id)
        );
        model.addAttribute("apprenti", apprenti);
        return "pages/ApprentiInfos";
    }

    @GetMapping("/search")
    public String search(@AuthenticationPrincipal User principal,Model model,
                         @RequestParam(required = false) String name,
                         @RequestParam(required = false) String enterprise,
                         @RequestParam(required = false) String promotion,
                         @RequestParam(required = false) String keywords) {
        TuteurEnseignant tuteur = tuteurRepo.findByLogin(principal.getUsername())
                .orElseThrow(() -> new RuntimeException("Tuteur non trouvé"));

        model.addAttribute("prenom", tuteur.getPrenom());
        model.addAttribute("nom", tuteur.getNom());

            List<Apprenti> apprentis = apprentiService.search(name, enterprise, promotion, keywords);
            model.addAttribute("apprentis", apprentis);
        return "pages/Search";
    }
}
