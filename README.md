🏆 Football Application - Authentication Service
Ce projet implémente un service d'authentification avancé dans une application de gestion de football, offrant plusieurs méthodes d'authentification sécurisées, dont l'authentification à deux facteurs (2FA) et la reconnaissance faciale.

🔑 Fonctionnalités d'authentification

📱 Authentification classique (email/mot de passe)
🔐 Authentification à deux facteurs (2FA) avec code temporaire
👤 Reconnaissance faciale (intégration avec service Python)
👥 Gestion des rôles (Admin, Spectateur)
🔄 Sessions utilisateurs avec suivi des connexions


🧩 Architecture du projet
Le projet est structuré selon une architecture MVC avec Spring Boot:
CopyFootball/
├── Application Spring Boot principale
├── Service Python de reconnaissance faciale

🛠️ Stack technique
Backend Java

Spring Boot - Framework principal
Spring Data JPA - Persistance des données
Spring Web - API REST
Project Lombok - Réduction du boilerplate
H2/MySQL - Base de données
TOTP - Authentification à deux facteurs

Service de reconnaissance faciale

Python - Langage de programmation
Flask - Serveur Web léger
face_recognition - Bibliothèque de reconnaissance faciale
OpenCV - Traitement d'image


📡 API REST
Authentification classique

POST /users/register - Inscription d'un nouvel utilisateur
POST /users/login - Connexion utilisateur

Authentification 2FA

POST /2fa/setup/{userId} - Configuration de l'authentification à deux facteurs
POST /2fa/verify - Vérification du code 2FA
POST /2fa/disable/{userId} - Désactivation de l'authentification à deux facteurs

Reconnaissance faciale

POST /face-auth/register/{userId} - Enregistrement du visage
POST /face-auth/verify/{userId} - Vérification par reconnaissance faciale

Gestion utilisateurs

GET /users/all - Liste des utilisateurs
GET /users/{id} - Détails d'un utilisateur
PUT /users/{id} - Mise à jour d'un utilisateur
DELETE /users/{id} - Suppression d'un utilisateur


🔧 Installation et configuration
Prérequis

Java 11+
Python 3.8+ (pour le service de reconnaissance faciale)
Maven

Service Spring Boot

Cloner le dépôt
Installer les dépendances: mvn install
Lancer l'application: mvn spring-boot:run

Service de reconnaissance faciale

Installer les dépendances: pip install -r requirements.txt
Lancer le service: python face_recognition_api.py


💼 Cas d'utilisation
Flux d'authentification 2FA

L'utilisateur s'inscrit via l'API /users/register
L'utilisateur configure 2FA via /2fa/setup/{userId}
L'utilisateur scanne le QR code avec Google Authenticator
Lors de la connexion via /users/login, le système demande un code 2FA
L'utilisateur fournit le code via /2fa/verify pour terminer l'authentification

Flux de reconnaissance faciale

L'utilisateur s'inscrit via l'API /users/register
L'utilisateur enregistre son visage via /face-auth/register/{userId}
Lors de la connexion, l'utilisateur peut s'authentifier par reconnaissance faciale via /face-auth/verify/{userId}


📋 Configuration avancée
Configuration de la 2FA
La bibliothèque TOTP est configurée pour générer des codes à 6 chiffres qui changent toutes les 30 secondes, conforme aux standards TOTP (RFC 6238).
Configuration de la reconnaissance faciale
Le service Python utilise un algorithme de reconnaissance faciale basé sur les encodages de visage avec une tolérance configurable.

🔒 Sécurité

Stockage sécurisé des secrets 2FA
Validation des entrées utilisateur
Protection contre les attaques par force brute
Détection de vivacité pour la reconnaissance faciale (prévention contre l'usurpation par photo)


📝 Notes de développement

La reconnaissance faciale nécessite un service Python séparé
Compatible avec les applications d'authentification standard comme Google Authenticator, Microsoft Authenticator et Authy
Pour un environnement de production, envisagez d'ajouter JWT pour l'authentification entre services


🧪 Tests avec Postman
Une collection Postman complète est disponible pour tester toutes les fonctionnalités d'authentification, y compris le flux 2FA et la reconnaissance faciale.