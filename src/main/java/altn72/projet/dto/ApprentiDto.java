package altn72.projet.dto;

public record ApprentiDto(
        Long id,
        String prenom,
        String nom,
        String email,
        String telephone,
        String adresse,
        String etat,
        Long entrepriseId,
        Long maitreId,
        Long tuteurId,
        Long anneeAcademiqueId
) {}
