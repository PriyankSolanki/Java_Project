package altn72.projet.controllers;

import altn72.projet.entities.TuteurEnseignant;
import altn72.projet.exceptions.TuteurEnseignantNotFoundException;
import altn72.projet.services.TuteurEnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.security.core.userdetails.User;

@ControllerAdvice
public class UserControllerAdvice {

    @Autowired
    private TuteurEnseignantService tuteurEnseignantService;

    @ModelAttribute("user")
    public TuteurEnseignant getCurrentUser(@AuthenticationPrincipal User principal) {
        if (principal == null) {
            return null;
        }
        return tuteurEnseignantService.getByLogin(principal.getUsername())
                .orElseThrow(() -> new TuteurEnseignantNotFoundException(null));
    }
}
