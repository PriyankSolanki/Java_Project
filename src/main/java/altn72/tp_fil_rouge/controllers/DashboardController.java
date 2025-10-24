package altn72.tp_fil_rouge.controllers;

import altn72.tp_fil_rouge.repositories.ApprentiRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui/dashboard")
public class DashboardController {

    private final ApprentiRepository apprentiRepo;

    public DashboardController(ApprentiRepository apprentiRepo) {
        this.apprentiRepo = apprentiRepo;
    }

    @GetMapping("/sans-maitre")
    public String apprentisSansMaitre(Model model) {
        var items = apprentiRepo.findByMaitreApprentissageIsNull();
        model.addAttribute("apprentis", items);
        model.addAttribute("total", items.size());
        return "dashboard_sans_maitre"; // -> templates/dashboard_sans_maitre.html
    }
}
