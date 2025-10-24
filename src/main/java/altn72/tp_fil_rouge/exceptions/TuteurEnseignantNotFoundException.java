package altn72.tp_fil_rouge.exceptions;

public class TuteurEnseignantNotFoundException extends RuntimeException {
    public TuteurEnseignantNotFoundException(Integer id) {
        super("Tuteur enseignant " + id + " not found");
    }
}
