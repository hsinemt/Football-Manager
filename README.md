# 🔐 Football Manager – User Service d'Authentification Sécurisée et Weather API

Ce service centralise l'authentification pour l'écosystème Football Manager avec des méthodes avancées de vérification d'identité.

## 🏗 Architecture Microservices

| Service                  | Port  | Statut | Description                          |
|--------------------------|-------|--------|--------------------------------------|
| 🧭 Eureka Server         | 8761  | ✅ Actif | Service de découverte centralisé     |
| 🛡️ API Gateway           | 8086  | ✅ Actif | Point d'entrée unique sécurisé       |
| 🔑 Service Authentification | 8079 | ✅ Actif | Gestion des identités                |


---

## 🌟 Fonctionnalités Clés

### 🔐 Méthodes d'Authentification
- 📧 Email/Mot de passe classique
- 🔢 2FA avec codes temporaires (TOTP)
- 👁️ Reconnaissance faciale biométrique
- 👥 Gestion fine des rôles (RBAC)

### 🛡️ Sécurité
- 🔄 Sessions utilisateur suivies
- 🛡️ Protection contre les attaques
- 📊 Monitoring Actuator
- 💾 Base H2 persistée

### ⚙️ Administration
- ✅ CRUD complet utilisateurs
- ⚙️ Console H2 intégrée
- 🔄 Auto-enregistrement Eureka

---

## 🛣 API Endpoints

### 👤 Gestion Utilisateurs
| Méthode | Endpoint               | Description                          |
|---------|------------------------|--------------------------------------|
| POST    | `/users/register`      | Création de compte                   |
| POST    | `/users/login`         | Connexion standard                   |
| GET     | `/users/all`           | Liste des utilisateurs               |
| GET     | `/users/{id}`          | Profil utilisateur                   |
| PUT     | `/users/{id}`          | Mise à jour profil                   |
| DELETE  | `/users/{id}`          | Désactivation compte                 |

### 🔐 2FA
| POST    | `/2fa/setup/{userId}`  | Activation 2FA                       |
| POST    | `/2fa/verify`          | Validation code                      |
| POST    | `/2fa/disable/{userId}`| Désactivation 2FA                    |

### 👁️ Reconnaissance Faciale
| GET    | `/weather/realtime	` | city, country (required) |


## 🛠 Stack Technique

### Backend Principal
```mermaid
pie
    title Technologies Java
    "Spring Boot" : 45
    "Spring Security" : 30
    "Spring Data JPA" : 15
<<<<<<< HEAD
    "TOTP" : 10
=======
    "TOTP" : 10

📊 Détection d'activité suspecte
