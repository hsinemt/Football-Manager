# Use Eclipse Temurin (OpenJDK) as the base image
FROM eclipse-temurin:17-jdk-jammy

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/api-gateway-*.jar api-gateway.jar

# Expose the API Gateway port (8090)
EXPOSE 8077

# Run the application
ENTRYPOINT ["java", "-jar", "api-gateway.jar"]