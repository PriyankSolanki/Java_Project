package altn72.tp_fil_rouge.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EntrepriseCreateRequest(
        @NotBlank @Size(max = 150) String raisonSociale,
        @Size(max = 255) String adresse,
        @Size(max = 255) String infosAcces
) {}
