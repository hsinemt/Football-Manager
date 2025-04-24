# Football Manager – Service Communication

This project is part of a microservices-based **Football Manager** system. It focuses on the **communication layer** between independent services including:

- **User Service**
- **Manager Service**
- **Team (Équipe) Service**
- **Competition Service**
- **Match Service**

## 🧩 Purpose

The goal of this project is to provide a **robust communication framework** between services. This may include:

- Event-based communication (e.g. using message brokers like RabbitMQ or Kafka)
- RESTful APIs between services
- Service discovery
- Data synchronization between services
- Ensuring loose coupling and scalability

## 📦 Services Overview

### 🔹 User Service
Manages player and admin users. Handles registration, authentication, and profiles.

### 🔹 Manager Service
Handles football managers — contracts, history, and assignments to teams.

### 🔹 Team (Équipe) Service
Manages teams, team stats, rosters, and team composition.

### 🔹 Competition Service
Organizes competitions, leagues, tournaments, fixtures, and standings.

### 🔹 Match Service
Manages match details — live updates, results, and events.



## 🚀 Getting Started

### Clone the Repository

```bash
git clone https://github.com/hsinemt/Football-Manager.git
cd Football-Manager
