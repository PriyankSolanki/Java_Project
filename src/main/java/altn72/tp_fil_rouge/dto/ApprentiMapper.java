package altn72.tp_fil_rouge.dto;

import altn72.tp_fil_rouge.entities.Apprenti;

public final class ApprentiMapper {
    private ApprentiMapper(){}

    public static ApprentiDto toDto(Apprenti a) {
        if (a == null) return null;
        Long entrepriseId = a.getEntreprise() != null ? a.getEntreprise().getId() : null;
        Long maitreId     = a.getMaitreApprentissage() != null ? a.getMaitreApprentissage().getId() : null;
        Long tuteurId     = a.getTuteurEnseignant() != null ? a.getTuteurEnseignant().getId() : null;
        Long anneeId      = a.getAnneeAcademique() != null ? a.getAnneeAcademique().getId() : null;

        return new ApprentiDto(
                a.getId(),
                a.getPrenom(),
                a.getNom(),
                a.getEmail(),
                a.getTelephone(),
                a.getAdresse(),
                a.getEtat(),
                entrepriseId,
                maitreId,
                tuteurId,
                anneeId
        );
    }
}
