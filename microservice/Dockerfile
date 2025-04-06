# Use Eclipse Temurin (OpenJDK) as the base image
FROM eclipse-temurin:17-jdk-jammy

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/microservice*.jar microservice.jar

# Expose the application port (8082)
EXPOSE 8082

# Run the application
ENTRYPOINT ["java", "-jar", "microservice.jar"]