package altn72.tp_fil_rouge.controleur;

import altn72.tp_fil_rouge.modele.Programmeur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ProgrammeurPageControleur {

    @Autowired
    private ProgrammeurService programmeurService;

    @GetMapping("/programmeur/{id}")
    public String afficherInfosProgrammeur(Model model, @PathVariable Integer id){
        Optional<Programmeur> prog = programmeurService.getProgrammeurById(id);
        if (prog.isPresent()){
            model.addAttribute("prog", prog.get());
            return "Programmeur";
        }else{
            return "error";
        }
    }

    @GetMapping("/programmeurs")
    public String afficherInfosProgrammeurs(Model model){
        List<Programmeur> prog = programmeurService.getProgrammeurs();
        model.addAttribute("prog", prog);
        return "Programmeurs";
    }

    @PostMapping("/programmeur/delete")
    public String supprimerProgrammeur(@RequestParam("id") Integer id){
        programmeurService.deleteProgrammeurById(id);
        return "redirect:/programmeurs";

    }

    @GetMapping("/addProgrammeur")
    public String addProgrammeur(Model model, @ModelAttribute Programmeur programmeur){
        System.out.println(programmeur.getPrenom());
        if(programmeur == null){
            programmeur = new Programmeur();
        }
        System.out.println(programmeur);
        model.addAttribute("programmeur", programmeur);
        return "addProgrammeur";
    }

    @PostMapping("/programmeur/add")
    public String supprimerProgrammeur(@ModelAttribute Programmeur programmeur){
        programmeurService.addProgrammeur(programmeur);
        return "redirect:/programmeurs";

    }


    @PostMapping("/putProgrammeur")
    public String putProgrammeur( Model model, @RequestParam("id") Integer id){
        Optional<Programmeur> programmeur = programmeurService.getProgrammeurById(id);
        if(programmeur.isPresent()){
            model.addAttribute("programmeur", programmeur.get());
        }else{
            model.addAttribute("programmeur", new Programmeur());
        }
        return "putProgrammeur";
    }

    @PostMapping("/programmeur/put")
    public String putProgrammeur( @ModelAttribute Programmeur programmeur){
        programmeurService.putProgrammeur(programmeur.getId(), programmeur);
        return "redirect:/programmeurs";
    }
}
