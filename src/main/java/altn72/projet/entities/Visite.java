package altn72.projet.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Entity
public class Visite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    private String format;
    private String commentaires;

    @ManyToOne
    private Apprenti apprenti;

}

