package altn72.tp_fil_rouge.controleur;

import altn72.tp_fil_rouge.modele.Programmeur;
import altn72.tp_fil_rouge.modele.ProgrammeurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProgrammeurService {

    @Autowired
    ProgrammeurRepository programmeurRepository;

    public List<Programmeur> getProgrammeurs() {
        return programmeurRepository.findAll();
    }

    public Optional<Programmeur> getProgrammeurById(Integer id) {

        Programmeur programmeur = programmeurRepository.findById(id).orElse(null);

        if(programmeur == null) {
            System.out.println("Programmeur n'existe pas");
            return Optional.empty();
        }else{
            return Optional.of(programmeur);
        }
    }

    public boolean deleteProgrammeurById(Integer id) {

        Programmeur programmeur = programmeurRepository.findById(id).orElse(null);

        if(programmeur == null) {
            System.out.println("Programmeur n'existe pas");
            return false;
        }else{
            programmeurRepository.deleteById(id);
            return true;
        }
    }

    public boolean putProgrammeur(Integer id, Programmeur programmeur) {
        Programmeur programmeurTrouver = programmeurRepository.findById(id).orElse(null);

        if(programmeurTrouver == null) {
            return false;
        }else{
            programmeurTrouver.setNom(programmeur.getNom());
            programmeurTrouver.setPrenom(programmeur.getPrenom());
            programmeurTrouver.setAdresse(programmeur.getAdresse());
            programmeurTrouver.setLangage(programmeur.getLangage());
            programmeurTrouver.setLivre(programmeur.getLivre());
            programmeurTrouver.setSalaire(programmeur.getSalaire());
            programmeurRepository.save(programmeurTrouver);
            programmeurRepository.flush();

            return true;
        }
    }

    public boolean addProgrammeur(Programmeur programmeur) {
        programmeurRepository.save(programmeur);
        programmeurRepository.flush();
        return true;
    }
}
