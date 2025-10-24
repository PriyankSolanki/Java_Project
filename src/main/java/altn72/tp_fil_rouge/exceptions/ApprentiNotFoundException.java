package altn72.tp_fil_rouge.exceptions;

public class ApprentiNotFoundException extends RuntimeException {
    public ApprentiNotFoundException(Integer id) {

        super("Apprenti " + id + " not found");
    }
}
