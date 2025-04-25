# Football Microservice Documentation

## Overview

The Football Microservice is a Spring Boot application that provides football-specific features including user management, authentication, and weather services for football matches. It is part of a larger microservices architecture for sports management.

## Features

- **User Management**: Registration, login, and profile management
- **Role-Based Access Control**: Different functionalities for Admin and Spectator users
- **Multi-Factor Authentication**: Support for two-factor authentication (2FA)
- **Face Recognition Authentication**: Alternative authentication method using face recognition
- **Weather Services**: Real-time weather data and forecasts for match locations
- **Keycloak Integration**: Secure authentication and authorization using Keycloak

## Architecture

### Technology Stack

- **Spring Boot 3.4.2**: Core framework
- **Spring Security**: Authentication and authorization
- **Spring Data JPA**: Data access layer
- **Spring Cloud**: Microservices integration
- **Keycloak**: Identity and access management
- **MySQL**: Primary database
- **Docker**: Containerization

### Microservice Integration

The Football microservice integrates with other services in the architecture:

- **Config Server**: Centralized configuration (port 8888)
- **Eureka Server**: Service discovery (port 8761)
- **API Gateway**: Entry point for client requests (port 8090)
- **Keycloak**: Authentication and authorization (port 8080)

## API Endpoints

### User Management

- **POST /users/register**: Register a new user
  ```json
  {
    "username": "example",
    "email": "user@example.com",
    "password": "password123",
    "role": "SPECTATOR"
  }
  ```

- **POST /users/login**: Login a user
  ```json
  {
    "email": "user@example.com",
    "password": "password123"
  }
  ```

- **GET /users/all**: Get all users (admin access)
- **GET /users/{id}**: Get a user by ID
- **PUT /users/{id}**: Update a user
- **DELETE /users/{id}**: Delete a user

### Admin-Specific Endpoints

- **POST /users/admin/{id}/manageTeams**: Admin functionality to manage teams
- **POST /users/admin/{id}/manageMatches**: Admin functionality to manage matches
- **POST /users/admin/{id}/superviseCompetition**: Admin functionality to supervise competitions

### Spectator-Specific Endpoints

- **POST /users/spectateur/{id}/followTeam**: Spectator functionality to follow a team
- **POST /users/spectateur/{id}/followMatch**: Spectator functionality to follow a match
- **POST /users/spectateur/{id}/followCompetition**: Spectator functionality to follow a competition

### Authentication

- **POST /two-factor-auth/setup**: Set up two-factor authentication
- **POST /two-factor-auth/verify**: Verify two-factor authentication code
- **POST /face-auth/authenticate**: Authenticate using face recognition

### Weather Services

- **GET /weather/realtime**: Get real-time weather for a location
  - Parameters: `city` (required), `country` (optional)
  - Returns: Temperature, humidity, pressure, wind speed, and weather conditions

- **GET /weather/forecast**: Get a 5-day weather forecast for a location
  - Parameters: `city` (required), `country` (optional)
  - Returns: Daily forecasts with temperature, humidity, and weather conditions

## Keycloak Integration

The Football microservice uses Keycloak for secure authentication and authorization:

### Configuration

The microservice is configured as an OAuth2 Resource Server that validates JWTs issued by Keycloak:

```properties
spring.security.oauth2.resourceserver.jwt.jwk-set-uri=http://keycloak:8080/realms/JobBoardKeycloak/protocol/openid-connect/certs
```

### Authentication Flow

1. Users register in the application
2. Login requests are processed by the application and forwarded to Keycloak
3. Keycloak issues JWT tokens upon successful authentication
4. The application validates these tokens for protected endpoints
5. Optional two-factor authentication or face recognition can be required

### Role-Based Access Control

- **Admin Role**: Can manage teams, matches, and competitions
- **Spectator Role**: Can follow teams, matches, and competitions

## Deployment

### Prerequisites

- Docker and Docker Compose
- JDK 17
- Maven (for local development)

### Environment Variables

- `SPRING_DATASOURCE_URL`: JDBC URL for the database
- `SPRING_DATASOURCE_USERNAME`: Database username
- `SPRING_DATASOURCE_PASSWORD`: Database password
- `KEYCLOAK_URL`: URL of the Keycloak server
- `OPENWEATHERMAP_API_KEY`: API key for OpenWeatherMap (for production weather data)

### Docker Deployment

The microservice can be deployed using Docker:

```bash
docker build -t football-service .
docker run -p 8060:8060 football-service
```

### Docker Compose Deployment

As part of the full microservices architecture:

```bash
docker-compose up -d
```

## Local Development

1. Clone the repository
2. Install dependencies: `mvn install`
3. Run the application: `mvn spring-boot:run`
4. Access the API at http://localhost:8060

## Security Considerations

- All sensitive endpoints are protected by OAuth2/JWT authentication
- Passwords are securely hashed and never stored in plain text
- Two-factor authentication adds an extra layer of security
- Face recognition provides a convenient and secure authentication option
- API requests should be made over HTTPS in production

## Troubleshooting

- Check application logs: `docker logs football-service`
- Verify Keycloak is running and accessible
- Ensure the database is properly initialized
- Check network connectivity between microservices