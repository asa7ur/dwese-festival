CREATE DATABASE IF NOT EXISTS FestivalDB;
USE FestivalDB;

CREATE TABLE IF NOT EXISTS Artists (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(20) UNIQUE,
    name VARCHAR(100),
    country VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS Stages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(20) UNIQUE,
    name VARCHAR(100),
    capacity INT
);

CREATE TABLE IF NOT EXISTS Performances (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(20) UNIQUE,
    date DATE,
    start_time TIME,
    end_time TIME,
    artist_id INT,
    stage_id INT,
    FOREIGN KEY (artist_id) REFERENCES Artists(id),
    FOREIGN KEY (stage_id) REFERENCES Stages(id)
);

CREATE TABLE IF NOT EXISTS Sponsors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(50) UNIQUE,
    name VARCHAR(100),
    phone VARCHAR(20),
    email VARCHAR(100),
    contribution DECIMAL(10,2)
);

CREATE TABLE IF NOT EXISTS SponsorStage (
    sponsor_id INT,
    stage_id INT,
    PRIMARY KEY (sponsor_id, stage_id),
    FOREIGN KEY (sponsor_id) REFERENCES Sponsors(id),
    FOREIGN KEY (stage_id) REFERENCES Stages(id)
);