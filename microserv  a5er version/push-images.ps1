# PowerShell script to build and push Docker images to Docker Hub
# Usage: .\push-images.ps1 <docker-hub-username>

param (
    [Parameter(Mandatory=$true)]
    [string]$DockerHubUsername
)

Write-Host "=== Building and pushing Docker images to Docker Hub ==="
Write-Host "Docker Hub Username: $DockerHubUsername"

# Prompt for Docker Hub password
$securePassword = Read-Host "Enter your Docker Hub password" -AsSecureString
$BSTR = [System.Runtime.InteropServices.Marshal]::SecureStringToBSTR($securePassword)
$DockerHubPassword = [System.Runtime.InteropServices.Marshal]::PtrToStringAuto($BSTR)

# Login to Docker Hub
Write-Host "Logging in to Docker Hub..."
docker login -u $DockerHubUsername -p $DockerHubPassword

# Array of services to build and push
$services = @(
    "config-server",
    "eureka",
    "competition-service",
    "player-service",
    "equipe-service",
    "match-service",
    "football-service",
    "manager-service",
    "api-gateway"
)

# Build and push each service
foreach ($service in $services) {
    $originalImageName = "sports-management/$service"
    $newImageName = "$DockerHubUsername/$service"
    
    Write-Host "Building $service..."
    docker-compose build $service
    
    Write-Host "Tagging $originalImageName:latest as $newImageName:latest"
    docker tag "$originalImageName`:latest" "$newImageName`:latest"
    
    Write-Host "Pushing $newImageName:latest to Docker Hub..."
    docker push "$newImageName`:latest"
    
    Write-Host "$service pushed successfully!"
    Write-Host "-----------------------------------"
}

Write-Host "All images have been successfully built and pushed to Docker Hub!"
Write-Host "To use these images, update your docker-compose.yml file to reference:"
Write-Host "$DockerHubUsername/<service-name>:latest"