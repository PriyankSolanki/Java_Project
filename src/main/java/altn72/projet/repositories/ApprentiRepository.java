package altn72.projet.repositories;

import altn72.projet.entities.Apprenti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApprentiRepository extends JpaRepository<Apprenti, Long> {

    List<Apprenti> findByMaitreApprentissageIsNull();
    List<Apprenti> findByEtatNot(String etat);

    @Query("""
    SELECT a FROM Apprenti a WHERE\s
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

    @Query(value = "SELECT * FROM apprenti WHERE etat = 'ACTIF'", nativeQuery = true)
    List<Apprenti> getApprentisActifs();

}