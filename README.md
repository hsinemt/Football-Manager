# ⚽ Football Manager – Microservice Joueurs

Ce projet fait partie d'une architecture **microservices** développée avec **Spring Boot**, **Spring Cloud Gateway**, **Eureka Server** et **H2 Database**. Il se concentre sur la gestion des joueurs, avec une API REST complète et des fonctionnalités supplémentaires comme des citations sportives.

---

## 🧩 Architecture du projet

- 🧭 **Eureka Server** – Service de découverte (port `8761`)
- 🛡️ **API Gateway** – Passerelle d’entrée (port `8086`)
- 👤 **Microservice Joueurs** – Service CRUD & statistiques (port `8077`)

---

## 📁 Fonctionnalités principales

- 🔄 CRUD des joueurs (ajouter, modifier, supprimer, consulter)
- 📊 Calcul de la **moyenne de buts** par joueur
- 🔍 Recherches avancées (nom, poste, âge, nationalité)
- 💬 Citations sportives et motivationnelles dynamiques
- 🌐 Enregistrement automatique dans **Eureka**
- 🛠️ Console **H2** activée (`/h2-console`)
- 📈 Monitoring avec **Spring Boot Actuator**

---

## 🔗 Routes exposées par le Microservice

| Méthode | Endpoint                       | Description                              |
|--------|--------------------------------|------------------------------------------|
| GET    | `/players/all`                 | Liste de tous les joueurs                |
| GET    | `/players/search/{id}`         | Détails d’un joueur par ID               |
| POST   | `/players/addPlayer`           | Ajouter un joueur                        |
| PUT    | `/players/{id}`                | Modifier un joueur                       |
| DELETE | `/players/{id}`                | Supprimer un joueur                      |
| GET    | `/players/stats/{id}`          | Moyenne de buts par match                |
| GET    | `/players/search`              | Recherche filtrée                        |
| GET    | `/players/sport`               | Citation sportive aléatoire              |
| GET    | `/players/motivation`          | Citation motivationnelle aléatoire       |

---

## ⚙️ Stack technique

- **Java 17+**
- **Spring Boot**
- **Spring Cloud Gateway**
- **Spring Cloud Netflix Eureka**
- **Spring Data JPA**
- **H2 Database (persistée)**
- **Spring Boot Actuator**
- **RESTful API**

---

## 🧪 Base de données H2

- 📍 Localisation : `playerdb` (mode fichier)
- 🔓 Console : [http://localhost:8077/h2-console](http://localhost:8077/h2-console)
- 👤 Identifiant : `player-service`
- 🛠️ DDL Auto : `update`

---

