package altn72.projet.dto;

import altn72.projet.entities.Entreprise;

public final class EntrepriseMapper {
    private EntrepriseMapper() {}

    public static EntrepriseDto toDto(Entreprise e) {
        return new EntrepriseDto(
                e.getId(),
                e.getRaisonSociale(),
                e.getAdresse(),
                e.getInfosAcces()
        );
    }
}
