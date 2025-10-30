package altn72.projet.ControllerAdvice;

import altn72.projet.api.ApiError;
import altn72.projet.api.FieldViolation;
import altn72.projet.exceptions.ApprentiNotFoundException;
import altn72.projet.exceptions.TuteurEnseignantNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpServletRequest req) {

        var status = HttpStatus.BAD_REQUEST;
        var api = new ApiError(status.value(), status.getReasonPhrase(),
                "Requête invalide : des champs contiennent des erreurs", req.getRequestURI());

        api.fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> new FieldViolation(
                        fe.getField(),
                        fe.getRejectedValue(),
                        fe.getDefaultMessage()))
                .toList();
        return ResponseEntity.status(status).body(api);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolation(
            ConstraintViolationException ex, HttpServletRequest req) {

        var status = HttpStatus.BAD_REQUEST;
        var api = new ApiError(status.value(), status.getReasonPhrase(),
                "Paramètres invalides", req.getRequestURI());


        api.fieldErrors = ex.getConstraintViolations().stream()
                .map(v -> new FieldViolation(
                        // last node => nom du paramètre/field
                        v.getPropertyPath().toString(),
                        v.getInvalidValue(),
                        v.getMessage()))
                .toList();
        return ResponseEntity.status(status).body(api);
    }

    @ExceptionHandler({ ResponseStatusException.class, ErrorResponseException.class })
    public ResponseEntity<ApiError> handleResponseStatusExceptions(
            Exception ex, HttpServletRequest req) {

        org.springframework.http.HttpStatusCode code =
                (ex instanceof ResponseStatusException rse) ? rse.getStatusCode()
                        : ((ErrorResponseException) ex).getStatusCode();

        org.springframework.http.HttpStatus httpStatus =
                (code instanceof org.springframework.http.HttpStatus hs) ? hs
                        : org.springframework.http.HttpStatus.resolve(code.value());

        String reason = (httpStatus != null) ? httpStatus.getReasonPhrase() : "Error";

        String message = (ex instanceof ResponseStatusException rse && rse.getReason() != null)
                ? rse.getReason()
                : ex.getMessage();

        ApiError api = new ApiError(code.value(), reason, message, req.getRequestURI());
        return ResponseEntity.status(code).body(api);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleNotReadable(
            HttpMessageNotReadableException ex, HttpServletRequest req) {

        var status = HttpStatus.BAD_REQUEST;
        var api = new ApiError(status.value(), status.getReasonPhrase(),
                "Corps de requête illisible ou invalide (JSON).", req.getRequestURI());
        return ResponseEntity.status(status).body(api);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAll(Exception ex, HttpServletRequest req) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        var api = new ApiError(status.value(), status.getReasonPhrase(),
                ex.getMessage(), req.getRequestURI());
        return new ResponseEntity<>(api, new HttpHeaders(), status);
    }

    @ExceptionHandler(ApprentiNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleApprentiNotFound(ApprentiNotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("erreur", ex.getMessage());
        body.put("status", HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(TuteurEnseignantNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleTuteurEnseignantNotFound(TuteurEnseignantNotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("erreur", ex.getMessage());
        body.put("status", HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}
