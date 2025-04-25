# Sports Management Microservices

This project is a microservices-based sports management system with multiple services for managing football teams, players, matches, and competitions.

## Architecture

The system consists of the following components:

- **Config Server**: Centralized configuration server (port 8888)
- **Eureka Server**: Service discovery (port 8761)
- **API Gateway**: Entry point for all client requests (port 8090)
- **Keycloak**: Authentication and authorization server (port 8080)
- **MySQL**: Main database for most services (port 3306)
- **MongoDB**: Database for the Manager service (port 27017)
- **phpMyAdmin**: Database management tool (port 8086)

### Microservices:

- **Competition Service**: Manages competitions (port 8077)
- **Player Service**: Manages players (port 8076)
- **Equipe Service**: Manages teams (port 8075)
- **Match Service**: Manages matches (port 8089)
- **Football Service**: Manages football-specific features (port 8060)
- **Manager Service**: Manages team managers (port 3000)

## Prerequisites

- Docker and Docker Compose installed
- At least 8GB of RAM allocated to Docker

## Getting Started

1. Clone the repository
2. Navigate to the project directory
3. Run the following command to start all services:

```bash
docker-compose up -d
```

4. To check the status of the services:

```bash
docker-compose ps
```

5. Access the services:
   - Eureka Dashboard: http://localhost:8761
   - API Gateway: http://localhost:8090
   - Keycloak Admin Console: http://localhost:8080
   - phpMyAdmin: http://localhost:8086 (Server: db, Username: root, Password: root)

## Stopping the Services

To stop all services:

```bash
docker-compose down
```

To stop and remove all containers, networks, and volumes:

```bash
docker-compose down -v
```

## Troubleshooting

If you encounter any issues:

1. Check the logs of a specific service:

```bash
docker-compose logs [service-name]
```

2. Restart a specific service:

```bash
docker-compose restart [service-name]
```

3. Rebuild a specific service:

```bash
docker-compose build [service-name]
docker-compose up -d [service-name]
```

## Pushing Docker Images to Registry

To push the Docker images to a registry (e.g., Docker Hub):

1. Make sure you have a Docker Hub account (or another registry account)
2. Run the provided script with your Docker Hub username:

   **For Windows (PowerShell):**
   ```powershell
   .\push-images.ps1 yourusername
   ```

   **For Linux/macOS (Bash):**
   ```bash
   chmod +x push-images.sh  # Make the script executable (first time only)
   ./push-images.sh yourusername
   ```

3. Enter your Docker Hub password when prompted
4. The script will:
   - Build all services using docker-compose
   - Tag them with your Docker Hub username
   - Push them to Docker Hub

5. After pushing, you can use these images in any environment by updating the image references in your docker-compose.yml:

```yaml
services:
  service-name:
    image: yourusername/service-name:latest
```

## Using Pre-built Images

If you want to use pre-built images instead of building them locally:

1. Update the docker-compose.yml file to use the images from Docker Hub:

```yaml
services:
  config-server:
    image: yourusername/config-server:latest
    # other configuration...

  eureka-server:
    image: yourusername/eureka:latest
    # other configuration...

  # And so on for other services...
```

2. Run docker-compose with the updated configuration:

```bash
docker-compose up -d
```

## Notes

- The initialization script creates the necessary databases and tables automatically
- The services are configured to wait for their dependencies to be ready before starting
- Keycloak is configured with a default admin user (admin/admin)
