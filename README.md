# 🔐 Football Manager – Service d'Authentification Sécurisée

Ce service centralise l'authentification pour l'écosystème Football Manager avec des méthodes avancées de vérification d'identité.

## 🏗 Architecture Microservices

| Service                  | Port  | Statut | Description                          |
|--------------------------|-------|--------|--------------------------------------|
| 🧭 Eureka Server         | 8761  | ✅ Actif | Service de découverte centralisé     |
| 🛡️ API Gateway           | 8086  | ✅ Actif | Point d'entrée unique sécurisé       |
| 🔑 Service Authentification | 8080 | ✅ Actif | Gestion des identités                |
| 🧠 Service Python (IA)   | 5000  | ✅ Actif | Reconnaissance faciale               |

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
| POST    | `/face-auth/register/{userId}` | Enregistrement visage |
| POST    | `/face-auth/verify/{userId}`   | Login biométrique     |

---

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
👁️ Reconnaissance Faciale
Enregistrement visage via /face-auth/register

Connexion via webcam /face-auth/verify

Validation IA Python

🔒 Mesures de Sécurité
🔄 Rotation automatique des secrets 2FA

🛡️ Chiffrement des données biométriques

⏲️ Expiration courte des tokens

📊 Détection d'activité suspecte

🧪 Tests de pénétration inclus
>>>>>>> 5fc56d734e6d4042ae5cf773d0ba75405b0f790b
