package altn72.projet.exceptions;

public class ApprentiNotFoundException extends RuntimeException {
    public ApprentiNotFoundException(Integer id) {

        super("Apprenti " + id + " not found");
    }
}
