
# Config‑Service

`Config-Service` is a Spring Boot microservice that centralizes all configuration properties for our Football‑Manager ecosystem. It exposes configuration values needed by the Competition, Match, Team, Player and User services, so you can change timeouts, URLs, feature‑flags and other settings in one place.

---

## Table of Contents

1. [Features](#features)  
2. [Getting Started](#getting‑started)  
3. [Configuration Properties](#configuration‑properties)  
   - [Competition Service](#competition‑service)  
   - [Match Service](#match‑service)  
   - [Team Service](#team‑service)  
   - [Player Service](#player‑service)  
   - [User Service](#user‑service)  
4. [Running Locally](#running‑locally)  
5. [Accessing Configs at Runtime](#accessing‑configs‑at‑runtime)  
6. [Contributing](#contributing)  
7. [License](#license)  

---

## Features

- Centralized YAML/Properties config for all domain services  
- Dynamic refresh via Spring Cloud Config (optional)  
- Environment‑specific profiles (`dev`, `staging`, `prod`)  
- Well‑documented keys and default values  

---

## Getting Started

1. Clone this repo and switch to the `Config-Service` branch.  
2. Edit configuration under `src/main/resources/` (application.yml or .properties).  
3. Build and run—downstream services will fetch from here.

---

## Configuration Properties

Below is the full list of properties this service exposes. You can override any of these in your downstream service by pointing its Spring Cloud Config client at this server.

### Competition Service

```yaml
competition:
  url: http://competition-service   # Base URL for competition API
  timeout:
    connect: 2s                      # connection timeout
    read: 5s                         # read timeout
  feature:
    allowRegistration: true          # enable new competition registration
    maxActiveCompetitions: 10        # maximum concurrent competitions
