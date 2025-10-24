package altn72.tp_fil_rouge.repositories;

import altn72.tp_fil_rouge.entities.Apprenti;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApprentiRepository extends JpaRepository<Apprenti, Long> {

    // Apprentis qui n'ont PAS de maître d'apprentissage
    List<Apprenti> findByMaitreApprentissageIsNull();

    // (optionnel) version qui charge aussi entreprise et tuteur pour éviter le N+1 dans Thymeleaf
    @EntityGraph(attributePaths = {"entreprise", "tuteurEnseignant"})
    List<Apprenti> findAllByMaitreApprentissageIsNull();
}
