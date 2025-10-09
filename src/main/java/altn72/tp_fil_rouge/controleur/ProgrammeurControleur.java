package altn72.tp_fil_rouge.controleur;

import altn72.tp_fil_rouge.modele.Programmeur;
import altn72.tp_fil_rouge.modele.ProgrammeurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProgrammeurControleur {

    @Autowired
    private ProgrammeurService programmeurService;

    @GetMapping("/tpfilerouge/programmeurs")
    public List<Programmeur> afficherInfosProgrammeurs(){
        return programmeurService.getProgrammeurs();
    }

    @GetMapping("/tpfilerouge/programmeur/{id}")
    public ResponseEntity<String> afficherInfosProgrammeur(@PathVariable Integer id){
        Optional<Programmeur> prog = programmeurService.getProgrammeurById(id);
        if (prog.isPresent()){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(prog);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Programmeur introuvable");
    }

    @DeleteMapping("/tpfilerouge/programmeur/{id}")
    public ResponseEntity<String> supprimerProgrammeur(@PathVariable Integer id){
        if (programmeurService.deleteProgrammeurById(id)){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Programmeur supprimé avec succès");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur lors de la suppression du programmeur");

    }

    @PutMapping("/tpfilerouge/programmeur/{id}")
    public ResponseEntity<String> modifierProgrammeur(@PathVariable Integer id, @RequestBody Programmeur programmeur){
        if (programmeurService.putProgrammeur(id,programmeur)){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Programmeur modifié avec succès");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur lors de la modification du programmeur");
    }

    @PostMapping("/tpfilerouge/programmeur")
    public ResponseEntity<String> ajouterProgrammeur(@RequestBody Programmeur programmeur){
        if (programmeurService.addProgrammeur(programmeur)){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Programmeur ajouté avec succès");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur lors de l'ajout du programmeur");
    }

}
