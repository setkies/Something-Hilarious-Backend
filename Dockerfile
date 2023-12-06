# Use the official OpenJDK base image
FROM openjdk:17-oracle

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY build/libs/SINP-0.0.1-SNAPSHOT.jar /app/Sinp.jar

# Expose the port that the application will run on
EXPOSE 8080

# Specify the command to run on container startup
CMD ["java", "-jar", "Sinp.jar"]
