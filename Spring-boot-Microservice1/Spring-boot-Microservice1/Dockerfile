# Utilisez une image OpenJDK 21 comme base
FROM adoptopenjdk:21-jre-hotspot

# Créez le répertoire de travail
WORKDIR /app

# Copiez le jar construit dans l'image
COPY build/libs/course-service.jar /course-service.jar

# Exposez le port sur lequel l'application s'exécute
EXPOSE 3333
# Commande pour démarrer l'application Spring Boot
CMD ["java", "-jar", "/app/course-service.jar"]



