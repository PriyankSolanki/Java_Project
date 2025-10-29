package altn72.projet.entities;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Apprenti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String prenom;
    private String nom;
    private String email;
    private String telephone;
    private String adresse;

    private String anneeLevel;
    private String etat;

    @ManyToOne
    private Entreprise entreprise;

    @ManyToOne
    private MaitreApprentissage maitreApprentissage;

    @ManyToOne
    private AnneeAcademique anneeAcademique;

    @ManyToOne
    private TuteurEnseignant tuteurEnseignant;

    @OneToOne(mappedBy = "apprenti", cascade = CascadeType.ALL)
    private EvaluationEcole evaluationEcole;

    @OneToOne(mappedBy = "apprenti", cascade = CascadeType.ALL)
    private Soutenance soutenance;

    @OneToMany(mappedBy = "apprenti", cascade = CascadeType.ALL)
    private List<Visite> visites;

    @Embedded
    private Mission mission;

    @Lob
    private String remarques;

    @Lob
    private String feedbackTuteurEnseignant;

}
