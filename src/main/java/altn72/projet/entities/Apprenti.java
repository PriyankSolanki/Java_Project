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

    @ManyToOne(cascade ={CascadeType.MERGE, CascadeType.PERSIST})
    private Entreprise entreprise;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private MaitreApprentissage maitreApprentissage;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private AnneeAcademique anneeAcademique;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private TuteurEnseignant tuteurEnseignant;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private EvaluationEcole evaluationEcole;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Soutenance soutenance;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Visite> visites;

    @Embedded
    private Mission mission;

    @Lob
    private String remarques;

    @Lob
    private String feedbackTuteurEnseignant;

}
