package altn72.tp_fil_rouge.config;


import altn72.tp_fil_rouge.entities.TuteurEnseignant;
import altn72.tp_fil_rouge.repositories.TuteurEnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

@ControllerAdvice
public class GlobalControllerAdvice {
    @Autowired
    private TuteurEnseignantRepository tuteurRepo;
    @ModelAttribute
    public void addUserInfo(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            Object principal = auth.getPrincipal();

            if (principal instanceof TuteurEnseignant customUser) {
                TuteurEnseignant tuteur = tuteurRepo.findByLogin(auth.getName())
                        .orElseThrow(() -> new RuntimeException("Tuteur non trouvé"));
                model.addAttribute("nom", tuteur.getNom());
            }
        }
    }
}
