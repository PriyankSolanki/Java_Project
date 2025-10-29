package altn72.projet.api;

public class FieldViolation {
    public String field;
    public Object rejectedValue;
    public String message;

    public FieldViolation(String field, Object rejectedValue, String message) {
        this.field = field; this.rejectedValue = rejectedValue; this.message = message;
    }
}
