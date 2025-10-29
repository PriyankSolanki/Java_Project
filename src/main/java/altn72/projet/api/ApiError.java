package altn72.projet.api;

import java.time.Instant;
import java.util.List;

public class ApiError {
    public Instant timestamp = Instant.now();
    public int status;
    public String error;
    public String message;
    public String path;
    public List<FieldViolation> fieldErrors;

    public ApiError(int status, String error, String message, String path) {
        this.status = status; this.error = error; this.message = message; this.path = path;
    }
}
