#!/bin/bash
# Bash script to build and push Docker images to Docker Hub
# Usage: ./push-images.sh <docker-hub-username>

# Check if username is provided
if [ $# -eq 0 ]; then
    echo "Error: Docker Hub username is required"
    echo "Usage: ./push-images.sh <docker-hub-username>"
    exit 1
fi

DOCKER_HUB_USERNAME=$1

echo "=== Building and pushing Docker images to Docker Hub ==="
echo "Docker Hub Username: $DOCKER_HUB_USERNAME"

# Prompt for Docker Hub password
echo -n "Enter your Docker Hub password: "
read -s DOCKER_HUB_PASSWORD
echo ""

# Login to Docker Hub
echo "Logging in to Docker Hub..."
echo "$DOCKER_HUB_PASSWORD" | docker login -u "$DOCKER_HUB_USERNAME" --password-stdin

# Array of services to build and push
services=(
    "config-server"
    "eureka"
    "competition-service"
    "player-service"
    "equipe-service"
    "match-service"
    "football-service"
    "manager-service"
    "api-gateway"
)

# Build and push each service
for service in "${services[@]}"; do
    ORIGINAL_IMAGE_NAME="sports-management/$service"
    NEW_IMAGE_NAME="$DOCKER_HUB_USERNAME/$service"
    
    echo "Building $service..."
    docker-compose build "$service"
    
    echo "Tagging $ORIGINAL_IMAGE_NAME:latest as $NEW_IMAGE_NAME:latest"
    docker tag "$ORIGINAL_IMAGE_NAME:latest" "$NEW_IMAGE_NAME:latest"
    
    echo "Pushing $NEW_IMAGE_NAME:latest to Docker Hub..."
    docker push "$NEW_IMAGE_NAME:latest"
    
    echo "$service pushed successfully!"
    echo "-----------------------------------"
done

echo "All images have been successfully built and pushed to Docker Hub!"
echo "To use these images, update your docker-compose.yml file to reference:"
echo "$DOCKER_HUB_USERNAME/<service-name>:latest"