package altn72.projet.repositories;

import altn72.projet.entities.AnneeAcademique;
import altn72.projet.entities.Apprenti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnneeAcademiqueRepository extends JpaRepository<AnneeAcademique, Long> {

}