package altn72.projet.entities;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Embeddable
public class Mission {
    private String motsCles; // DevOps node.js CI/CD
    private String metierCible;
    private String commentaires;

}
