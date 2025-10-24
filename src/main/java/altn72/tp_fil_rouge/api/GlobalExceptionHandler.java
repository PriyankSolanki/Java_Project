package altn72.tp_fil_rouge.api;

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

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 1) @Valid sur un body JSON -> erreurs de validation des champs (DTO)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpServletRequest req) {

        var status = HttpStatus.BAD_REQUEST;
        var api = new ApiError(status.value(), status.getReasonPhrase(),
                "Requête invalide : des champs contiennent des erreurs", req.getRequestURI());

        var fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> new FieldViolation(
                        fe.getField(),
                        fe.getRejectedValue(),
                        fe.getDefaultMessage()))
                .toList();

        api.fieldErrors = fieldErrors;
        return ResponseEntity.status(status).body(api);
    }

    // 2) @Validated sur path/query params -> violations de contraintes
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolation(
            ConstraintViolationException ex, HttpServletRequest req) {

        var status = HttpStatus.BAD_REQUEST;
        var api = new ApiError(status.value(), status.getReasonPhrase(),
                "Paramètres invalides", req.getRequestURI());

        var fieldErrors = ex.getConstraintViolations().stream()
                .map(v -> new FieldViolation(
                        // last node => nom du paramètre/field
                        v.getPropertyPath().toString(),
                        v.getInvalidValue(),
                        v.getMessage()))
                .toList();

        api.fieldErrors = fieldErrors;
        return ResponseEntity.status(status).body(api);
    }

    // 3) Exceptions métier déjà mappées avec un status (ex: 404 via ResponseStatusException)
    @ExceptionHandler({ ResponseStatusException.class, ErrorResponseException.class })
    public ResponseEntity<ApiError> handleResponseStatusExceptions(
            Exception ex, HttpServletRequest req) {

        // 1) Récupérer le HttpStatusCode (Spring 6)
        org.springframework.http.HttpStatusCode code =
                (ex instanceof ResponseStatusException rse) ? rse.getStatusCode()
                        : ((ErrorResponseException) ex).getStatusCode();

        // 2) Convertir en HttpStatus pour obtenir le reason phrase (si possible)
        org.springframework.http.HttpStatus httpStatus =
                (code instanceof org.springframework.http.HttpStatus hs) ? hs
                        : org.springframework.http.HttpStatus.resolve(code.value());

        String reason = (httpStatus != null) ? httpStatus.getReasonPhrase() : "Error";

        // 3) Message lisible (si ResponseStatusException avec reason dédié, l'utiliser)
        String message = (ex instanceof ResponseStatusException rse && rse.getReason() != null)
                ? rse.getReason()
                : ex.getMessage();

        ApiError api = new ApiError(code.value(), reason, message, req.getRequestURI());
        return ResponseEntity.status(code).body(api);
    }

    // 4) JSON mal formé / type invalide
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleNotReadable(
            HttpMessageNotReadableException ex, HttpServletRequest req) {

        var status = HttpStatus.BAD_REQUEST;
        var api = new ApiError(status.value(), status.getReasonPhrase(),
                "Corps de requête illisible ou invalide (JSON).", req.getRequestURI());
        return ResponseEntity.status(status).body(api);
    }

    // 5) Fallback 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAll(Exception ex, HttpServletRequest req) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        var api = new ApiError(status.value(), status.getReasonPhrase(),
                ex.getMessage(), req.getRequestURI());
        return new ResponseEntity<>(api, new HttpHeaders(), status);
    }
}
