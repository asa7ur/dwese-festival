CREATE TABLE patrocinadores (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(100),
    aporte DECIMAL(10,2) NOT NULL,
    escenario_id INT,
    FOREIGN KEY (escenario_id) REFERENCES escenario(id)
);

CREATE TABLE espectadores (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    edad INT CHECK (edad >= 18),
    telefono VARCHAR(20),
    email VARCHAR(100),
    ciudad VARCHAR(50),
    pais VARCHAR(50)
);
