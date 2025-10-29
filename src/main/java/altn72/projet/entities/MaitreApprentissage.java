package altn72.projet.entities;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class MaitreApprentissage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String prenom;
    private String nom;
    private String poste;
    private String email;
    private String telephone;

    @OneToMany(mappedBy = "maitreApprentissage", cascade = CascadeType.ALL)
    private List<Apprenti> apprentis;

}
