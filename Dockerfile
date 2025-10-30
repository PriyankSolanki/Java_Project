# Étape 1 : Build du projet avec Maven
FROM maven:3.9.8-eclipse-temurin-21 AS builder

# Définir le dossier de travail
WORKDIR /app

# Copier le pom.xml et télécharger les dépendances
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copier le code source
COPY src ./src

# Construire le JAR final
RUN mvn clean package -DskipTests

# Étape 2 : Image finale légère
FROM eclipse-temurin:21-jdk-alpine

# Dossier de travail dans le conteneur
WORKDIR /app

# Copier le JAR généré depuis l’étape précédente
COPY --from=builder /app/target/*.jar app.jar

# Variables d’environnement (modifiables)
ENV SERVER_PORT=8080

# Exposer le port du conteneur
EXPOSE 8080

# Commande de démarrage
ENTRYPOINT ["java", "-jar", "app.jar"]
