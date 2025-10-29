package altn72.projet.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class EvaluationEcole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String theme;
    private double noteFinale;
    private String commentaires;

    @OneToOne(mappedBy = "evaluationEcole")
    private Apprenti apprenti;


}
