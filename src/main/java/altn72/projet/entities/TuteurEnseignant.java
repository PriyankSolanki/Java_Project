package altn72.projet.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
public class TuteurEnseignant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String prenom;
    private String nom;
    private String email;
    private String login;
    private String motDePasse;

    @OneToMany(mappedBy = "tuteurEnseignant", cascade = CascadeType.ALL)
    private List<Apprenti> apprentis;

}
