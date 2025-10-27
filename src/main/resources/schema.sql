CREATE TABLE artistas (
    id INT PRIMARY KEY,
    nombre VARCHAR(100),
    pais VARCHAR(50),
    escenario_id INT
);

CREATE TABLE tickets (
    id INT PRIMARY KEY,
    precio DECIMAL(8,2),
    id_espectador INT,
    FOREIGN KEY (id_espectador) REFERENCES espectador(id)
);

CREATE TABLE escenarios (
    id INT PRIMARY KEY,
    nombre VARCHAR(100),
    capacidad INT
);


