# Utilisez l'image de base avec Java 21
FROM adoptopenjdk:21-jre-hotspot

# Copiez le jar construit dans l'image
COPY build/libs/api-gateway.jar /app/api-gateway.jar

# Définissez le répertoire de travail
WORKDIR /app

# Exposez le port sur lequel l'application s'exécute
EXPOSE 5555

# Commande pour exécuter l'application
CMD ["java", "-jar", "api-gateway.jar"]
