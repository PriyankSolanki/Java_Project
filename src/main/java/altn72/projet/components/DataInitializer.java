package altn72.projet.components;


import altn72.projet.repositories.*;
import altn72.projet.entities.*;
import altn72.projet.services.TuteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ApprentiRepository apprentiRepo;
    @Autowired
    private EntrepriseRepository entrepriseRepo;
    @Autowired
    private MaitreApprentissageRepository maitreRepo;
    @Autowired
    private AnneeAcademiqueRepository anneeRepo;
    @Autowired
    private VisiteRepository visiteRepo;
    @Autowired
    private EvaluationEcoleRepository evalRepo;
    @Autowired
    private SoutenanceRepository soutenanceRepo;
    @Autowired
    private TuteurService tuteurService;


    @Override
    public void run(String... args){
        if (apprentiRepo.count() > 0) {
            System.out.println("Tuteur initialisé : login : jaugustin / mdp : password123");
            return;
        }

        TuteurEnseignant tuteur = new TuteurEnseignant();
        tuteur.setPrenom("Jacques");
        tuteur.setNom("Augustin");
        tuteur.setLogin("jaugustin");
        tuteur.setMotDePasse("password123");
        tuteur.setEmail("jacques.augustin@efrei.fr");
        tuteurService.saveTuteur(tuteur);

        AnneeAcademique annee2025 = new AnneeAcademique();
        annee2025.setAnnee("2025-2026");
        annee2025.setActive(true);
        annee2025 = anneeRepo.save(annee2025);

        AnneeAcademique annee2024 = new AnneeAcademique();
        annee2024.setAnnee("2024-2025");
        annee2024.setActive(false);
        anneeRepo.save(annee2024);


        Entreprise entreprise1 = new Entreprise();
        entreprise1.setRaisonSociale("TechCorp");
        entreprise1.setAdresse("10 rue de Paris, 75001 Paris");
        entreprise1.setInfosAcces("Badge requis, 2ème étage");
        entrepriseRepo.save(entreprise1);

        Entreprise entreprise2 = new Entreprise();
        entreprise2.setRaisonSociale("Innovatech");
        entreprise2.setAdresse("20 avenue des Champs, 75008 Paris");
        entreprise2.setInfosAcces("Badge requis, 3ème étage");
        entrepriseRepo.save(entreprise2);

        MaitreApprentissage maitre1 = new MaitreApprentissage();
        maitre1.setNom("Durand");
        maitre1.setPrenom("Paul");
        maitre1.setEmail("p.durand@techcorp.com");
        maitre1.setTelephone("0612345678");
        maitre1.setPoste("Responsable DevOps");
        maitreRepo.save(maitre1);

        MaitreApprentissage maitre2 = new MaitreApprentissage();
        maitre2.setNom("Martin");
        maitre2.setPrenom("Claire");
        maitre2.setEmail("c.martin@innovatech.com");
        maitre2.setTelephone("0698765432");
        maitre2.setPoste("Chef de projet");
        maitreRepo.save(maitre2);

        Soutenance soutenance1 = new Soutenance();
        soutenance1.setDate(LocalDate.of(2025, 11, 20));
        soutenance1.setNoteFinale(17.0);
        soutenance1.setCommentaires("Présentation claire et structurée");
        soutenanceRepo.save(soutenance1);

        Soutenance soutenance2 = new Soutenance();
        soutenance2.setDate(LocalDate.of(2025, 11, 22));
        soutenance2.setNoteFinale(15.0);
        soutenance2.setCommentaires("Bonne présentation mais manque de détails techniques");
        soutenanceRepo.save(soutenance2);

        EvaluationEcole eval1 = new EvaluationEcole();
        eval1.setTheme("Rapport DevOps");
        eval1.setNoteFinale(16.5);
        eval1.setCommentaires("Excellent travail, bonne méthodologie");
        evalRepo.save(eval1);

        EvaluationEcole eval2 = new EvaluationEcole();
        eval2.setTheme("Projet Node.js");
        eval2.setNoteFinale(14.0);
        eval2.setCommentaires("Travail correct mais à améliorer");
        evalRepo.save(eval2);

        Apprenti apprenti1 = new Apprenti();
        apprenti1.setNom("Leclerc");
        apprenti1.setPrenom("Sophie");
        apprenti1.setEmail("s.leclerc@mail.com");
        apprenti1.setTelephone("0611223344");
        apprenti1.setAdresse("5 rue Victor Hugo, 75005 Paris");
        apprenti1.setEntreprise(entreprise1);
        apprenti1.setMaitreApprentissage(maitre1);
        apprenti1.setTuteurEnseignant(tuteur);
        apprenti1.setAnneeAcademique(annee2025);
        apprenti1.setSoutenance(soutenance1);
        apprenti1.setEvaluationEcole(eval1);
        apprenti1.setEtat("ACTIF");

        Mission mission1 = new Mission();
        mission1.setMotsCles("DevOps, CI/CD");
        mission1.setMetierCible("Ingénieur DevOps");
        mission1.setCommentaires("Mission très intéressante");
        apprenti1.setMission(mission1);

        apprentiRepo.save(apprenti1);

        Apprenti apprenti2 = new Apprenti();
        apprenti2.setNom("Dubois");
        apprenti2.setPrenom("Lucas");
        apprenti2.setEmail("l.dubois@mail.com");
        apprenti2.setTelephone("0622334455");
        apprenti2.setAdresse("12 avenue de la République, 75011 Paris");
        apprenti2.setEntreprise(entreprise2);
        apprenti2.setMaitreApprentissage(maitre2);
        apprenti2.setTuteurEnseignant(tuteur);
        apprenti2.setAnneeAcademique(annee2025);
        apprenti2.setSoutenance(soutenance2);
        apprenti2.setEvaluationEcole(eval2);
        apprenti2.setEtat("ACTIF");

        Mission mission2 = new Mission();
        mission2.setMotsCles("Node.js, Angular");
        mission2.setMetierCible("Développeur Fullstack");
        mission2.setCommentaires("Travail sur projet interne");
        apprenti2.setMission(mission2);

        apprentiRepo.save(apprenti2);

        Visite visite1 = new Visite();
        visite1.setApprenti(apprenti1);
        visite1.setDate(LocalDate.of(2025, 10, 5));
        visite1.setFormat("Présentiel");
        visite1.setCommentaires("Très bonne progression");
        visiteRepo.save(visite1);

        Visite visite2 = new Visite();
        visite2.setApprenti(apprenti2);
        visite2.setDate(LocalDate.of(2025, 10, 6));
        visite2.setFormat("Visio");
        visite2.setCommentaires("Besoin d'accompagnement supplémentaire");
        visiteRepo.save(visite2);

        System.out.println("Base de données initialisée avec succès !");
    }
}
