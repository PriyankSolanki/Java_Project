package altn72.tp_fil_rouge.entities;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String raisonSociale;
    private String adresse;
    private String infosAcces; // badge, etage...

    @OneToMany(mappedBy = "entreprise", cascade = CascadeType.ALL)
    private List<Apprenti> apprentis;

}
