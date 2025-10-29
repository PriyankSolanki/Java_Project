package altn72.projet.repositories;

import altn72.projet.entities.TuteurEnseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface TuteurEnseignantRepository extends JpaRepository<TuteurEnseignant, Long> {
    Optional<TuteurEnseignant> findByLogin(String login);
}