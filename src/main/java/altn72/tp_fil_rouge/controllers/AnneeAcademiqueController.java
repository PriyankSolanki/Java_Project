package altn72.tp_fil_rouge.controllers;

import altn72.tp_fil_rouge.entities.AnneeAcademique;
import altn72.tp_fil_rouge.entities.Apprenti;
import altn72.tp_fil_rouge.services.AnneeAcademiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AnneeAcademiqueController {

    @Autowired
    private AnneeAcademiqueService  anneeAcademiqueService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<AnneeAcademique> anneeAcademiques = anneeAcademiqueService.getAll();

        AnneeAcademique anneeAcademique = anneeAcademiques.stream()
                .filter(AnneeAcademique::isActive)
                .findFirst()
                .orElse(null);

        List<Apprenti> apprentis = (anneeAcademique!=null) ? anneeAcademique.getApprentis() : new ArrayList<>();

        model.addAttribute("apprentis", apprentis);
        model.addAttribute("anneeAcademique", anneeAcademique);
        return "Dashboard";
    }


}
