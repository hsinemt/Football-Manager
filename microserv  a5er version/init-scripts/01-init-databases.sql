-- Create databases if they don't exist
CREATE DATABASE IF NOT EXISTS sports_management;
CREATE DATABASE IF NOT EXISTS keycloak;

-- Use the sports_management database
USE sports_management;

-- Create tables for the different services
-- These are basic schemas that should be adapted based on the actual entity models

-- Match Service tables
CREATE TABLE IF NOT EXISTS matches (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    match_date DATETIME,
    location VARCHAR(255),
    home_team_id BIGINT,
    away_team_id BIGINT,
    home_score INT,
    away_score INT,
    status VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Player Service tables
CREATE TABLE IF NOT EXISTS players (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    birth_date DATE,
    position VARCHAR(50),
    jersey_number INT,
    team_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Equipe Service tables
CREATE TABLE IF NOT EXISTS teams (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    country VARCHAR(100),
    founded_year INT,
    logo_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Competition Service tables
CREATE TABLE IF NOT EXISTS competitions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    start_date DATE,
    end_date DATE,
    type VARCHAR(50),
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS competition_teams (
    competition_id BIGINT,
    team_id BIGINT,
    PRIMARY KEY (competition_id, team_id),
    FOREIGN KEY (competition_id) REFERENCES competitions(id),
    FOREIGN KEY (team_id) REFERENCES teams(id)
);

-- Football Service tables
CREATE TABLE IF NOT EXISTS football_events (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    match_id BIGINT,
    event_type VARCHAR(50),
    player_id BIGINT,
    minute INT,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (match_id) REFERENCES matches(id)
);

-- Grant privileges
GRANT ALL PRIVILEGES ON sports_management.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON keycloak.* TO 'root'@'%';
FLUSH PRIVILEGES;