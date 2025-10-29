package altn72.projet.dto;

import java.util.ArrayList;
import java.util.List;

public class ImportCsvReport {
    public int totalRows;
    public int success;
    public int failed;
    public List<RowSuccess> successes = new ArrayList<>();
    public List<RowError> errors = new ArrayList<>();

    public static class RowSuccess {
        public int line;          // numéro de ligne (1 = header)
        public Long apprentiId;   // id créé
        public String email;      // pour information
        public String prenom;     // >>> AJOUT
        public String nom;        // >>> AJOUT

        // Constructeur HISTORIQUE (reste compatible)
        public RowSuccess(int line, Long apprentiId, String email) {
            this(line, apprentiId, email, null, null);
        }

        // Nouveau constructeur avec prenom/nom
        public RowSuccess(int line, Long apprentiId, String email, String prenom, String nom) {
            this.line = line;
            this.apprentiId = apprentiId;
            this.email = email;
            this.prenom = prenom;
            this.nom = nom;
        }
    }

    public static class RowError {
        public int line;
        public String message;    // message lisible (validation / 404 / etc.)
        public RowError(int line, String message) {
            this.line = line;
            this.message = message;
        }
    }
}
