## Rapport pour le projet de JAVA

### 3.1 -
Identifiants de connexion au projet :  
- User : jaugustin  
- MDP : password123

### 3.2 -
Nous avons utilisé Intellij pour le développement de notre application.

Nous avons utilisé comme SGBD :  
- PostgreSQL, MariaDB et MySQL

### 3.3 -
Url d'accès du site hébergé : https://java-project-bt1s.onrender.com/login
Url d'accès du site en local : http://localhost:8080/login

La base de données est hébergé vie Supabase, mais si vous voulez utiliser une base de données locale, vous pouvez changer les paramètres dans application.properties.
La base de données se créé et des données de tests s'insèrent automatiquement lors du premier lancement.
De plus un scrip sql (script_database.sql) est disponible à la racine du projet.
Un fichier CSV est disponible à la racine du projet (import_apprentis.csv) pour vous permettre de tester l'ajout des apprenties via csv.

### 3.4 -
#### a -
Nous pensons que la méthode avec laquelle nous avons travaillé était intéressante. Diviser les tâches pour avancer ensemble sur nos différents outils a permis de nous faire gagner du temps, et d'avancer pleinement sur nos fonctionnalités.

#### b -
La grande difficulté que nous avons rencontrée a été le merge de toutes nos parties. Une bonne communication et un travail rigoureux de tous les membres du groupe nous a aidé à surmonter cette difficulté.

#### c -
Nous nous sommes rapidement bien répartis et équitablement le travail dans ce projet. Chaque personne au sein du groupe à grandement contribué au fonctionnement de l'application.

#### d -
Les 3 points qui nous ont marqué pendant ce cours sont :  
- La clarter des TP, et le suivit du TP fil rouge était une approche très intéressante et nous a bien aidé à comprendre le fonctionnement d'un projet JAVA.  
- Le projet est bien adapté aux cours et nous permet d'exploiter et de développer nos connaissances en plus de nous laisser une liberté pour en avoir de nouvelles.
- Les Quiz ont été un bon moyen d'évaluer nos connaissances, sans nous contraindre à écrire du code sur papier.

#### e -
Nous pensons qu'avec plus de temps, nous aurions pu perfectionner l'IHM. Cependant, étant en période de fin de modules, nous avons beaucoup de travail personnel à réaliser au niveau de l'école, donc nous avons voulu prioriser le fonctionnement pur de l'application. Nous aurions aimer développer et améliorer d'avantage de fonctionnalitées mais le temps nous à manquer...

#### f —
Notre projet est conforme aux principes **SOLID** :

- **S**  
  Séparation claire des couches : controllers, services, repositories (8 interfaces JPA), entities, ...  
  L’initialisation BDD et la sécurité (BCrypt) sont isolées de la logique métier.

- **O**  
  Le système est ouvert à l’extension, fermé à la modification :
    - ajout de nouvelles méthodes de JpaRepository ou de nouveaux services sans toucher au code existant
    - comportement configurable via application.properties
    - composants remplaçables par configuration.

- **L**  
  Les controllers et services utilisent des interfaces. Ainsi, chaque nouvelle implémentation respectant le contrat peut être utilisée

- **I**  
  Granularité par agrégat : 8 Repos spécialisés au lieu d’une grosse interface god object.  
  Les contrats de sécurité UserDetailsService n’imposent pas de méthodes inutiles.

- **D**  
  Les couches haut niveau dépendent d’abstractions, pas de classes concrètes :
    - services exposent des interfaces
    - Spring injecte les implémentations concrètes via injection par constructeur
