# 🎓 Projet ALTN72 – Gestion des Apprentis 

## 👨‍💻Groupe
- Priyank SOLANKI
- Younes KHOYA
- Victor BONNIN

## 📘 Description

Ce projet est une application **Spring Boot + Thymeleaf** permettant la **gestion complète des apprentis**, de leurs **entreprises**, **tuteurs**, **soutenances**, **évaluations** et **visites**.  
Il s’inscrit dans le cadre du **TP Fil Rouge** du module ALTN72 à l’**EFREI Paris**.

L’application permet :
- de **créer, modifier, consulter et supprimer** des apprentis,
- de **lier un apprenti** à son **tuteur enseignant**, **maître d’apprentissage**, **entreprise**, et **année académique**,
- de **gérer les soutenances, missions, évaluations et visites**,
- d’**initialiser automatiquement** la base de données avec des données de démonstration,
- et d’**exposer une documentation Swagger** pour les endpoints REST.

---

## 🏗️ Technologies utilisées

| Catégorie | Technologie |
|------------|-------------|
| **Backend** | Java 21, Spring Boot 3.5.x |
| **Frontend (templating)** | Thymeleaf |
| **Base de données** | PostgreSQL (hébergée sur Supabase) |
| **ORM / Persistence** | Hibernate / JPA |
| **Build / Gestion de dépendances** | Maven |
| **Documentation API** | Swagger (Springdoc OpenAPI 2.x) |
| **Gestion des exceptions** | `@ControllerAdvice`, exceptions personnalisées |
| **Lombok** | Pour la génération automatique des getters/setters |

---

## ⚙️ Configuration du projet

### 🧩 Prérequis
- Java 21+
- Maven 3.9+
- Un IDE compatible (IntelliJ, VSCode, Eclipse…)

---

### 🍦Git
```bash
git clone https://github.com/PriyankSolanki/Java_Project.git
cd <nom-du-projet
```

```bash
mvn spring-boot:run
```

### 🧩 Base de données
La base de données est hébergé via avec Supabase.
Des données de tests sont déjà présent.

### 🔓API Swagger
```
http://localhost:8080/swagger-ui/index.html
```