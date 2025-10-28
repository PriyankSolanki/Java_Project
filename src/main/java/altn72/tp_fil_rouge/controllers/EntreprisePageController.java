package altn72.tp_fil_rouge.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui/entreprises")
public class EntreprisePageController {

    @GetMapping("/nouveau")
    public String nouveau() {
        return "entreprise_create"; // -> templates/entreprise_create.html
    }
}
