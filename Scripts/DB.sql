-- Crear base de datos
CREATE DATABASE IF NOT EXISTS sistemaDB;
USE sistemaDB;

-- tabla rol
CREATE TABLE rol (
    id_rol INT(2) PRIMARY KEY,
    nombre_rol VARCHAR(20)
);

-- Insertar roles
INSERT INTO rol (id_rol, nombre_rol) VALUES (1,'ADMINISTRADOR');
INSERT INTO rol (id_rol, nombre_rol) VALUES (2,'ALMACENISTA');

-- tabla cat permisos de rol
CREATE TABLE permisos (
	id_permiso INT(2) PRIMARY KEY,
    permiso VARCHAR(25),
    id_rol INT(2),
    FOREIGN KEY (id_rol) REFERENCES rol (id_rol)
);

-- Insertar permisos en la tabla
-- Permisos para Administrador (idRol = 1)
INSERT INTO permisos (id_permiso, permiso, id_rol) VALUES (1, 'ver_inventario', 1);
INSERT INTO permisos (id_permiso, permiso, id_rol) VALUES (2, 'agregar_producto', 1);
INSERT INTO permisos (id_permiso, permiso, id_rol) VALUES (3, 'aumentar_inventario', 1);
INSERT INTO permisos (id_permiso, permiso, id_rol) VALUES (4, 'dar_baja_producto', 1);
INSERT INTO permisos (id_permiso, permiso, id_rol) VALUES (5, 'ver_historico', 1);

-- Permisos para Almacenista (idRol = 2)
INSERT INTO permisos (id_permiso, permiso, id_rol) VALUES (6, 'ver_inventario', 2);
INSERT INTO permisos (id_permiso, permiso, id_rol) VALUES (7, 'ver_salida_productos', 2);
INSERT INTO permisos (id_permiso, permiso, id_rol) VALUES (8, 'sacar_inventario', 2);

-- tabla usuario
CREATE TABLE usuario (
    id_usuario INT(6) PRIMARY KEY,
    nombre VARCHAR(100),
    correo VARCHAR(50),
    contrasena VARCHAR(25),
    id_rol INT(2),
    estatus INT(1),
    FOREIGN KEY (id_rol) REFERENCES rol (id_rol)
);

-- tabla productos
CREATE TABLE producto (
	id_producto INT(6) PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50),
    cantidad INT DEFAULT 0,
    estatus INT(1)
);

-- tabla historico
CREATE TABLE historico (
	id_historico INT(6) PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT(6),
    id_producto INT(6),
    movimiento VARCHAR(10),
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario),
    FOREIGN KEY (id_producto) REFERENCES producto (id_producto)
);

-- Usuarios para pruebas
INSERT INTO usuario (id_usuario, nombre, correo, contrasena, id_rol, estatus) VALUES (1, "Administrador", "admin@email.com", "12345a", 1, 1);
INSERT INTO usuario (id_usuario, nombre, correo, contrasena, id_rol, estatus) VALUES (2, "Almacenista", "almacen@email.com", "12345a", 2, 1);
