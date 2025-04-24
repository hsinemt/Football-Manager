Manager Application
A full-stack application for managing football managers, allowing users to create, update, delete, and assign/unassign teams to managers. The application consists of an Angular frontend, a Node.js/Express backend with MongoDB, and integrates with Eureka for service discovery in a microservices architecture.
Table of Contents

Project Overview
Features
Technologies
Architecture
Prerequisites
Installation
Backend Setup
Frontend Setup
Eureka Server Setup


Usage
API Endpoints
Environment Variables
Troubleshooting
Contributing
License
Contact

Project Overview
The Manager Application allows users to manage football managers by performing CRUD operations and assigning/unassigning teams. The backend is a Node.js/Express microservice that communicates with MongoDB and registers with a Eureka server for service discovery. The frontend is built with Angular and provides a user-friendly interface to interact with the backend API.
This project is designed as part of a microservices architecture, with the backend (MICROSERVICE5) interacting with other services via Eureka.
Features

Manager Management:
Create, read, update, and delete managers.
Assign a manager to a team or unassign them.


Team Integration:
Fetch teams from another microservice (via EquipeService).
Display teams in a dropdown for assignment.


Responsive UI:
Built with Angular and Bootstrap for a clean, responsive design.
Includes modals for adding/editing managers and a table for listing them.


Service Discovery:
Backend registers with Eureka for discoverability in a microservices ecosystem.


Error Handling:
Friendly error messages for users (e.g., "Manager not found", "Server error").
Backend logging for debugging.



Technologies

Frontend:
Angular 16+
Bootstrap (via bootstrap CSS classes)
RxJS (for handling HTTP requests)


Backend:
Node.js 16+
Express.js
MongoDB (with Mongoose ORM)
Socket.IO (for potential real-time updates)


Service Discovery:
Eureka (Netflix Eureka Server)


Dependencies:
cors (for handling CORS in the backend)
eureka-js-client (for Eureka integration)
mongoose (for MongoDB interaction)



Architecture

Frontend (http://localhost:4200):
Angular application that communicates with the backend via REST API.
Uses ManagerService to interact with the backend and EquipeService to fetch teams.


Backend (http://localhost:3000):
Node.js/Express microservice (MICROSERVICE5).
Connects to MongoDB for data storage.
Registers with Eureka for service discovery.


Database:
MongoDB stores manager data (schema includes nom, prenom, equipe, nb_match_carriere, nb_victoire, nb_titre, historique).


Service Discovery:
Eureka server (http://localhost:8761) manages microservices registration and discovery.



Prerequisites
Ensure the following are installed on your system:

Node.js (v16 or higher) and npm (v8 or higher)
Download: Node.js


MongoDB (v4 or higher)
Install and run MongoDB locally: MongoDB Installation


Angular CLI (v16 or higher)
Install: npm install -g @angular/cli


Eureka Server
A running Eureka server (e.g., a Spring Boot Eureka Server) on http://localhost:8761.
Example: Use a Spring Boot project with @EnableEurekaServer.



Installation
Backend Setup

Clone the Repository:git clone https://github.com/your-username/manager-app.git
cd manager-app


Navigate to Backend Directory (if separate, or root if combined):cd backend


Install Dependencies:npm install


Configure MongoDB:
Create a config/db.json file in the root directory:{
  "mongo": {
    "uri": "mongodb://localhost:27017/managerDB"
  }
}


Replace managerDB with your database name.


Start the Backend:node server.js


The server will run on http://localhost:3000.
Logs will confirm MongoDB connection and Eureka registration:MongoDB connected
Server is running on port 3000
Eureka registration successful





Frontend Setup

Navigate to Frontend Directory:cd frontend


Install Dependencies:npm install


Configure API URL:
Update src/environments/environment.ts and environment.prod.ts:export const environment = {
  production: false,
  apiUrl: 'http://localhost:3000'
};

export const environment = {
  production: true,
  apiUrl: 'http://localhost:3000'
};




Start the Frontend:ng serve


The frontend will run on http://localhost:4200.



Eureka Server Setup

Run Eureka Server:
Use a Spring Boot application with @EnableEurekaServer.
Example pom.xml dependency for Eureka:<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>


Run the Eureka server on http://localhost:8761.


Verify Registration:
Open http://localhost:8761 in your browser.
Confirm that MICROSERVICE5 is listed under "Instances currently registered with Eureka".



Usage

Start MongoDB:mongod


Start Eureka Server (if not already running).
Start the Backend:node server.js


Start the Frontend:ng serve


Access the Application:
Open http://localhost:4200/managers in your browser.
Use the UI to:
Add a new manager via the "Add Manager" button.
Edit or delete managers using the "Edit" and "Delete" buttons.
Assign a team to a manager using the "Assign to Team" dropdown.
Unassign a team using the "Unassign Team" button.





API Endpoints
The backend exposes the following REST API endpoints:



Method
Endpoint
Description
Request Body Example



GET
/managers
Fetch all managers
N/A


GET
/managers/:id
Fetch a manager by ID
N/A


POST
/managers
Create a new manager
{"nom": "Guardiola", "prenom": "Pep"}


PUT
/managers/:id
Update a manager by ID
{"nom": "Klopp", "prenom": "Jurgen"}


DELETE
/managers/:id
Delete a manager by ID
N/A


PATCH
/managers/:id/assign-team
Assign/unassign a team
{"equipe": "5"} or {"equipe": null}


Example: Create a Manager
curl -X POST http://localhost:3000/managers \
-H "Content-Type: application/json" \
-d '{"nom": "Guardiola", "prenom": "Pep", "nb_match_carriere": 800, "nb_victoire": 600, "nb_titre": 32, "historique": ["Barcelona", "Bayern Munich"]}'

Example: Assign a Team
curl -X PATCH http://localhost:3000/managers/680a0c58a5f3cc12433302cc/assign-team \
-H "Content-Type: application/json" \
-d '{"equipe": "5"}'

Example: Unassign a Team
curl -X PATCH http://localhost:3000/managers/680a0c58a5f3cc12433302cc/assign-team \
-H "Content-Type: application/json" \
-d '{"equipe": null}'

Environment Variables
The project uses the following configuration files and environment variables:

Backend:
config/db.json: MongoDB connection URI.{
  "mongo": {
    "uri": "mongodb://localhost:27017/managerDB"
  }
}




Frontend:
src/environments/environment.ts:export const environment = {
  production: false,
  apiUrl: 'http://localhost:3000'
};





Troubleshooting

CORS Errors:
Ensure the backend (server.js) has CORS middleware enabled:app.use(cors({ origin: 'http://localhost:4200', methods: ['GET', 'POST', 'PUT', 'DELETE', 'PATCH', 'OPTIONS'], allowedHeaders: ['Content-Type'] }));


Verify the frontend’s apiUrl matches the backend port (http://localhost:3000).


Manager Not Found (404):
Check MongoDB to ensure the manager ID exists:mongo
use managerDB
db.managers.find({"_id": ObjectId("680a0c58a5f3cc12433302cc")})


If the manager doesn’t exist, add a new manager via the UI.


Eureka Registration Failed:
Ensure the Eureka server is running on http://localhost:8761.
Check for port conflicts on 3000:lsof -i :3000
kill -9 <PID>




MongoDB Connection Error:
Verify MongoDB is running:mongod


Check the config/db.json file for the correct URI.



Contributing
Contributions are welcome! To contribute:

Fork the repository.
Create a new branch:git checkout -b feature/your-feature


Make your changes and commit:git commit -m "Add your feature"


Push to the branch:git push origin feature/your-feature


Create a Pull Request on GitHub.

Please ensure your code follows the project’s coding style and includes appropriate tests.
License
This project is licensed under the MIT License - see the LICENSE file for details.
Contact
For questions or feedback, please contact:

Your Name: oussama.sfaxi@esprit.tn
GitHub: oussamasfaxi

