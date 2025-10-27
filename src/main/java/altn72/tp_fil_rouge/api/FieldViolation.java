package altn72.tp_fil_rouge.api;

public class FieldViolation {
    public String field;        // ex: "email"
    public Object rejectedValue;// ex: "test" (sans @)
    public String message;      // ex: "doit être une adresse e-mail valide"

    public FieldViolation() {}
    public FieldViolation(String field, Object rejectedValue, String message) {
        this.field = field; this.rejectedValue = rejectedValue; this.message = message;
    }
}
