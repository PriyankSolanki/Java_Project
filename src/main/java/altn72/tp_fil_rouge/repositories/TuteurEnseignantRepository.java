package altn72.tp_fil_rouge.repositories;

import altn72.tp_fil_rouge.entities.TuteurEnseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface TuteurEnseignantRepository extends JpaRepository<TuteurEnseignant, Long> {
    Optional<TuteurEnseignant> findByLogin(String login);
}