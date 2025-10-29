package altn72.tp_fil_rouge.controllers;

import altn72.tp_fil_rouge.entities.TuteurEnseignant;
import altn72.tp_fil_rouge.repositories.TuteurEnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.security.core.userdetails.User;

@ControllerAdvice
public class UserControllerAdvice {

    @Autowired
    private TuteurEnseignantRepository tuteurRepo;

    @ModelAttribute("user")
    public TuteurEnseignant getCurrentUser(@AuthenticationPrincipal User principal) {
        if (principal == null) {
            return null;
        }
        return tuteurRepo.findByLogin(principal.getUsername())
                .orElseThrow(() -> new RuntimeException("Tuteur non trouvé"));
    }
}
