package altn72.projet.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ApprentiCreateRequest(
        @NotBlank @Size(max = 100) String prenom,
        @NotBlank @Size(max = 100) String nom,
        @NotBlank @Email          String email,
        @Size(max = 20)           String telephone,
        @Size(max = 255)          String adresse,

        // valeurs libres dans ton modèle (ex: "ACTIF", "INACTIF"…)
        String etat,
        String anneeLevel,

        // relations (optionnelles) passées par IDs
        Long entrepriseId,
        Long maitreId,
        Long tuteurId,
        Long anneeAcademiqueId,

        // optionnels
        String remarques,
        String feedbackTuteurEnseignant
) {}
