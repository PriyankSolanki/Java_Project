package altn72.tp_fil_rouge.dto;

import altn72.tp_fil_rouge.entities.Entreprise;

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
