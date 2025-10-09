package altn72.tp_fil_rouge.modele;

import lombok.Data;

@Data
public class ProgrammeurOld {

    private Integer idProgrammeur;
    private String nom;
    private String prenom;
    private String adresse;
    private String langageExpertise;
    private String livrePrefere;
    private Double salaire;

    public ProgrammeurOld(Integer idProgrammeur, String nom, String prenom, String adresse, String langageExpertise, String livrePrefere, Double salaire) {
        this.idProgrammeur = idProgrammeur;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.langageExpertise = langageExpertise;
        this.livrePrefere = livrePrefere;
        this.salaire = salaire;
    }
}
