package altn72.projet.api;

import java.time.Instant;
import java.util.List;

public class ApiError {
    public Instant timestamp = Instant.now();
    public int status;              // 400, 404, 422, 500...
    public String error;            // "Bad Request"
    public String message;          // message global
    public String path;             // /api/apprentis
    public List<FieldViolation> fieldErrors; // erreurs par champ (optionnel)

    public ApiError() {}
    public ApiError(int status, String error, String message, String path) {
        this.status = status; this.error = error; this.message = message; this.path = path;
    }
}
