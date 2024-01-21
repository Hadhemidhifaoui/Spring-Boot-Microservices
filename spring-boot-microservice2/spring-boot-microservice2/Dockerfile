# Utilisez l'image de base avec Java 21
FROM adoptopenjdk:21-jre-hotspot

# Copiez le jar construit dans l'image
COPY build/libs/purchase-service.jar /app/purchase-service.jar

# Définissez le répertoire de travail
WORKDIR /app

# Exposez le port sur lequel l'application s'exécute
EXPOSE 4444

# Commande pour exécuter l'application
CMD ["java", "-jar", "purchase-service.jar"]
