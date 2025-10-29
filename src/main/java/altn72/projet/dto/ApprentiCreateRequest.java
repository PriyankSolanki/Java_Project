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

        String etat,
        String anneeLevel,

        Long entrepriseId,
        Long maitreId,
        Long tuteurId,
        Long anneeAcademiqueId,

        String remarques,
        String feedbackTuteurEnseignant
) {}
