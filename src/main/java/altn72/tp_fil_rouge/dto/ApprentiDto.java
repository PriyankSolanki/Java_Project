package altn72.tp_fil_rouge.dto;

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
