
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
CREATE TABLE IF NOT EXISTS Spectators (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(20) UNIQUE,
    name VARCHAR(50),
    last_name VARCHAR(100),
    birth_date DATE,
    phone VARCHAR(20),
    email VARCHAR(100),
    city VARCHAR(100),
    country VARCHAR(50)
);