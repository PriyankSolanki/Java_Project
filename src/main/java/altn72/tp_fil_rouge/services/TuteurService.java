package altn72.tp_fil_rouge.services;



import altn72.tp_fil_rouge.entities.TuteurEnseignant;
import altn72.tp_fil_rouge.repositories.TuteurEnseignantRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TuteurService implements UserDetailsService {

    private final TuteurEnseignantRepository repo;
    private final PasswordEncoder passwordEncoder;

    public TuteurService(TuteurEnseignantRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        TuteurEnseignant tuteur = repo.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Tuteur non trouvé"));
        return User.builder()
                .username(tuteur.getLogin())
                .password(tuteur.getMotDePasse())
                .roles("TUTEUR")
                .build();
    }

    public void saveTuteur(TuteurEnseignant tuteur) {
        tuteur.setMotDePasse(passwordEncoder.encode(tuteur.getMotDePasse()));
        repo.save(tuteur);
    }
}
