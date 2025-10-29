package altn72.projet.controllers;



import altn72.projet.services.TuteurService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    private final TuteurService tuteurService;

    public AuthController(TuteurService tuteurService) {
        this.tuteurService = tuteurService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
