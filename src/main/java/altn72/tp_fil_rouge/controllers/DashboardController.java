package altn72.tp_fil_rouge.controllers;



import altn72.tp_fil_rouge.entities.TuteurEnseignant;
import altn72.tp_fil_rouge.repositories.TuteurEnseignantRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final TuteurEnseignantRepository tuteurRepo;

    public DashboardController(TuteurEnseignantRepository tuteurRepo) {
        this.tuteurRepo = tuteurRepo;
    }

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal User principal, Model model) {
        // récupère le TuteurEnseignant connecté via son login
        TuteurEnseignant tuteur = tuteurRepo.findByLogin(principal.getUsername())
                .orElseThrow(() -> new RuntimeException("Tuteur non trouvé"));

        model.addAttribute("prenom", tuteur.getPrenom());
        model.addAttribute("nom", tuteur.getNom());
        return "dashboard";
    }
}
