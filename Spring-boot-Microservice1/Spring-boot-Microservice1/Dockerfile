# Utilisez une image OpenJDK 21 comme base
FROM adoptopenjdk:21-jre-hotspot

# Créez le répertoire de travail
WORKDIR /app

# Copiez le jar construit dans l'image
COPY build/libs/cours-service.jar /app/cours-service.jar

# Commande pour démarrer l'application Spring Boot
CMD ["java", "-jar", "/app/cours-service.jar"]
