1.11. 
- L'application est compilé.
- Build
- Exécution de l'application

1.12.
Tomcat started on port 8080 (http) with context 
path '/', signifie que le projet démarre sur le 
port 8080 et que le contexte path est '/'.

1.14.
@SpringBootApplication, permet de spécifier que 
c'est une application SpringBoot et de créé des objets
liés à Spring
SpringApplication.run(TpFilRougeApplication.class, args);,
a pour but de run notre application.

1.15. 
Car Springboot charge les class en mémoire,
il faut alors les recompiler à chaque changement.
De plus il crée aussi un contexte qui comprend 
tous les beans.

2.1. L'application scan le fichier de base  grace au component scan et les instructions qui suit
run peut perturber le lancement et les résultats ne sont pas stable.

2.2.3 Le component scan trouve les autres composants du projet et les ajoute au context path.

3.1.3. Cette dépendence permet de communiquer avec une base de données, 
elle attend alors une configuration de connexion pour une base de données.

4.1.1. Directement via une injection du repository, le JPA possède déjà des méthodes toutes faites.

4.1.2. Oui via une instance du repository.

4.1.7. Nous sommes dans la partie persistence.

4.1.8. L'annotation @Autowired n'est pas une bonne pratique, 

4.5. PATCH permet de modifier partiellement une entité, PUT met à jour tous les champs de l'entité.

4.6. La class ResponseEntity permet d'avoir un maximum d'info dans le retour d'un endpoint, c'est très
utile dans la création d'une API, ça nous permet d'avoir une structure complete pour les réponses.









