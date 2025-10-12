package altn72.tp_fil_rouge.entities;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class AnneeAcademique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String annee;
    private boolean active;

    @OneToMany(mappedBy = "anneeAcademique", cascade = CascadeType.ALL)
    private List<Apprenti> apprentis;

}
