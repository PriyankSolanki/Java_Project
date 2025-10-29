package altn72.projet.exceptions;

public class TuteurEnseignantNotFoundException extends RuntimeException {
    public TuteurEnseignantNotFoundException(Integer id) {
        super("Tuteur enseignant " + id + " not found");
    }
}
