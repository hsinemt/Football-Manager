
# Use Eclipse Temurin (OpenJDK) as the base image
FROM eclipse-temurin:17-jdk-jammy

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/eureka-server-0.0.1-SNAPSHOT.jar eureka-server.jar

# Expose the Eureka server port (8761)
EXPOSE 8761

# Run the application
ENTRYPOINT ["java", "-jar", "eureka-server.jar"]