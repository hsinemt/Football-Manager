const express = require('express');
const http = require('http');
const path = require('path');
const db = require('./config/db.json');
const mongoose = require('mongoose');
const socketIo = require('socket.io');
const manager = require('./routers/Manager');
const { Eureka } = require('eureka-js-client');

const app = express();
const server = http.createServer(app);
const io = socketIo(server);
const PORT = 3000;

// View engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'twig');

// Middleware
app.use(express.json());
app.use(express.urlencoded({ extended: false }));

// Routes
app.use('/managers', manager);

// Health check endpoint for Eureka
app.get('/actuator/health', (req, res) => {
  res.json({ status: 'UP' });
});

// Connect to MongoDB
mongoose.connect(db.mongo.uri)
  .then(() => console.log('MongoDB connected'))
  .catch(err => console.error('MongoDB connection error:', err));

// Start server
server.listen(PORT, () => {
  console.log('====================================');
  console.log(`Server is running on port ${PORT}`);
  console.log('====================================');
});

// Eureka client setup
const eurekaClient = new Eureka({
  instance: {
    app: 'MICROSERVICE5',
    instanceId: 'microservice5-nodejs',
    hostName: 'localhost',
    ipAddr: '127.0.0.1',
    port: {
      '$': PORT,
      '@enabled': true,
    },
    vipAddress: 'microservice5',
    dataCenterInfo: {
      '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
      name: 'MyOwn',
    },
    healthCheckUrl: `http://localhost:${PORT}/actuator/health`,
    statusPageUrl: `http://localhost:${PORT}/actuator/health`,
  },
  eureka: {
    host: 'localhost',
    port: 8761,
    servicePath: '/eureka/apps/',
  },
});

// Register with Eureka
eurekaClient.start((error) => {
  if (error) {
    console.error('Eureka registration failed:', error);
  } else {
    console.log('Eureka registration successful');
  }
});
