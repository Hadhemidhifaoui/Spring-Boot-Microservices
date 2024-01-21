# Utilisez l'image de base avec Java 21
FROM openjdk:21-jdk-alpine

# Copiez le jar construit dans l'image
COPY build/libs/eureka-server.jar /app/eureka-server.jar

# Définissez le répertoire de travail
WORKDIR /app

# Exposez le port sur lequel l'application s'exécute
EXPOSE 6666

# Commande pour exécuter l'application
CMD ["java", "-jar", "eureka-server.jar"]

