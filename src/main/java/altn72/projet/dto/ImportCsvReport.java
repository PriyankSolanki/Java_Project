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
        public int line;
        public Long apprentiId;
        public String email;
        public String prenom;
        public String nom;

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
        public String message;
        public RowError(int line, String message) {
            this.line = line;
            this.message = message;
        }
    }
}
