package altn72.tp_fil_rouge.repositories;

import altn72.tp_fil_rouge.entities.Apprenti;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.List;

public interface ApprentiRepository extends JpaRepository<Apprenti, Long> {

    // Apprentis qui n'ont PAS de maître d'apprentissage
    List<Apprenti> findByMaitreApprentissageIsNull();

    // (optionnel) version qui charge aussi entreprise et tuteur pour éviter le N+1 dans Thymeleaf
    @EntityGraph(attributePaths = {"entreprise", "tuteurEnseignant"})
    List<Apprenti> findAllByMaitreApprentissageIsNull();

    @Query("""
    SELECT a FROM Apprenti a WHERE 
      (LOWER(a.prenom) LIKE LOWER(CONCAT('%',:name, '%')) OR LOWER(a.nom) LIKE LOWER(CONCAT('%',:name,'%'))) AND
      (LOWER(a.mission.motsCles) LIKE LOWER(CONCAT('%',:keywords,'%')) )AND
      (LOWER(a.entreprise.raisonSociale) LIKE LOWER(CONCAT('%',:enterprise, '%'))) AND
      (LOWER(a.anneeAcademique.annee) LIKE LOWER(CONCAT('%',:promotion, '%')))""")
    List<Apprenti> search(
            @Param("name") String name,
            @Param("enterprise") String enterprise,
            @Param("promotion") String promotion,
            @Param("keywords") String keywords
    );
}