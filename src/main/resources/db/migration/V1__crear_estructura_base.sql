-- =============================================
-- Crear la base de datos
-- =============================================
CREATE DATABASE IF NOT EXISTS foro_hub CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE foro_hub;

-- =============================================
-- Tabla: categoria
-- =============================================
CREATE TABLE categoria (
                           id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID único de la categoría',
                           nombre VARCHAR(50) NOT NULL UNIQUE COMMENT 'Nombre único de la categoría',
                           descripcion TEXT COMMENT 'Descripción opcional de la categoría',
                           fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de creación del registro',
                           fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de última modificación'
) COMMENT 'Tabla que almacena las categorías de los cursos';

-- =============================================
-- Tabla: curso
-- =============================================
CREATE TABLE curso (
                       id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID único del curso',
                       nombre VARCHAR(100) NOT NULL COMMENT 'Nombre del curso',
                       categoria_id INT NOT NULL COMMENT 'Relación con la categoría',
                       fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de creación del registro',
                       fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de última modificación',
                       CONSTRAINT fk_curso_categoria FOREIGN KEY (categoria_id) REFERENCES categoria(id)
) COMMENT 'Tabla que almacena los cursos ofrecidos en el foro';

-- =============================================
-- Tabla: usuario
-- =============================================
CREATE TABLE usuario (
                         id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID único del usuario',
                         nombre VARCHAR(100) NOT NULL COMMENT 'Nombre completo del usuario',
                         correo_electronico VARCHAR(100) NOT NULL UNIQUE COMMENT 'Correo electrónico único del usuario',
                         contrasena VARCHAR(255) NOT NULL COMMENT 'Contraseña encriptada del usuario',
                         fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de creación del registro',
                         fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de última modificación'
) COMMENT 'Tabla que almacena los datos de los usuarios registrados en el foro';

-- =============================================
-- Tabla: perfil
-- =============================================
CREATE TABLE perfil (
                        id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID único del perfil',
                        nombre VARCHAR(50) NOT NULL UNIQUE COMMENT 'Nombre único del perfil (e.g., ADMIN, USER)',
                        fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de creación del registro',
                        fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de última modificación'
) COMMENT 'Tabla que define los diferentes perfiles o roles de los usuarios';

-- =============================================
-- Tabla: usuario_perfil
-- =============================================
CREATE TABLE usuario_perfil (
                                usuario_id INT NOT NULL COMMENT 'ID del usuario',
                                perfil_id INT NOT NULL COMMENT 'ID del perfil',
                                fecha_asignacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de asignación del perfil',
                                PRIMARY KEY (usuario_id, perfil_id) COMMENT 'Llave compuesta para evitar duplicados',
                                CONSTRAINT fk_usuario_perfil_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id),
                                CONSTRAINT fk_usuario_perfil_perfil FOREIGN KEY (perfil_id) REFERENCES perfil(id)
) COMMENT 'Tabla intermedia para gestionar la relación muchos a muchos entre usuario y perfil';

-- =============================================
-- Tabla: topico
-- =============================================
CREATE TABLE topico (
                        id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID único del tópico',
                        titulo VARCHAR(150) NOT NULL COMMENT 'Título del tópico',
                        mensaje VARCHAR(255) NOT NULL COMMENT 'Mensaje inicial del tópico',
                        status ENUM('ABIERTO', 'CERRADO', 'RESUELTO') DEFAULT 'ABIERTO' COMMENT 'Estado del tópico',
                        autor_id INT NOT NULL COMMENT 'ID del autor del tópico',
                        curso_id INT NOT NULL COMMENT 'ID del curso relacionado con el tópico',
                        fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de creación del registro',
                        fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de última modificación',
                        CONSTRAINT fk_topico_usuario FOREIGN KEY (autor_id) REFERENCES usuario(id),
                        CONSTRAINT fk_topico_curso FOREIGN KEY (curso_id) REFERENCES curso(id),
                        CONSTRAINT unique_titulo_mensaje UNIQUE (titulo, mensaje)
) COMMENT 'Tabla que almacena los tópicos (discusiones) creados en el foro';

-- =============================================
-- Tabla: respuesta
-- =============================================
CREATE TABLE respuesta (
                           id INT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID único de la respuesta',
                           mensaje TEXT NOT NULL COMMENT 'Contenido de la respuesta',
                           topico_id INT NOT NULL COMMENT 'Relación con la tabla "topico"',
                           autor_id INT NOT NULL COMMENT 'ID del autor de la respuesta',
                           solucion BOOLEAN DEFAULT FALSE COMMENT 'Indica si la respuesta es la solución aceptada',
                           fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de creación del registro',
                           fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de última modificación',
                           CONSTRAINT fk_respuesta_topico FOREIGN KEY (topico_id) REFERENCES topico(id),
                           CONSTRAINT fk_respuesta_usuario FOREIGN KEY (autor_id) REFERENCES usuario(id)
) COMMENT 'Tabla que almacena las respuestas asociadas a los tópicos';

-- =============================================
-- Poblar la tabla: categoria
-- =============================================
INSERT INTO categoria (nombre, descripcion)
VALUES
    ('Programación', 'Cursos relacionados con lenguajes y técnicas de programación.'),
    ('Base de Datos', 'Cursos sobre diseño, administración y optimización de bases de datos.'),
    ('DevOps', 'Cursos sobre automatización y prácticas de integración continua.');

-- =============================================
-- Poblar la tabla: curso
-- =============================================
INSERT INTO curso (nombre, categoria_id)
VALUES
    ('Java Básico', 1),  -- Pertenece a la categoría "Programación"
    ('MySQL Avanzado', 2), -- Pertenece a la categoría "Base de Datos"
    ('Docker para Principiantes', 3); -- Pertenece a la categoría "DevOps"

-- =============================================
-- Poblar la tabla: perfil
-- =============================================
INSERT INTO perfil (nombre)
VALUES
    ('ADMIN'),  -- Perfil de administrador
    ('USER');   -- Perfil de usuario regular

-- =============================================
-- Poblar la tabla: usuario
-- =============================================
INSERT INTO usuario (nombre, correo_electronico, contrasena)
VALUES
    ('Juan Pérez', 'juan.perez@example.com', '12345'), -- Usuario 1
    ('Maria López', 'maria.lopez@example.com', 'abcde'), -- Usuario 2
    ('Carlos García', 'carlos.garcia@example.com', 'pass123'); -- Usuario 3

-- =============================================
-- Poblar la tabla: usuario_perfil
-- =============================================
INSERT INTO usuario_perfil (usuario_id, perfil_id)
VALUES
    (1, 1),  -- Juan Pérez es ADMIN
    (2, 2),  -- Maria López es USER
    (3, 2);  -- Carlos García es USER

-- =============================================
-- Poblar la tabla: topico
-- =============================================
INSERT INTO topico (titulo, mensaje, status, autor_id, curso_id)
VALUES
    ('Introducción a Java', '¿Cómo inicio con Java Básico?', 'ABIERTO', 1, 1), -- Juan Pérez, Curso Java Básico
    ('Consulta sobre MySQL', '¿Cómo optimizar consultas complejas?', 'ABIERTO', 2, 2), -- Maria López, Curso MySQL Avanzado
    ('Problemas con Docker', 'Mi contenedor no inicia, ¿qué hago?', 'ABIERTO', 3, 3); -- Carlos García, Curso Docker

-- =============================================
-- Poblar la tabla: respuesta
-- =============================================
INSERT INTO respuesta (mensaje, topico_id, autor_id, solucion)
VALUES
    ('Debes instalar el JDK primero.', 1, 2, FALSE), -- Respuesta de Maria López al tópico 1
    ('Utiliza índices en las columnas clave.', 2, 1, TRUE), -- Respuesta de Juan Pérez al tópico 2
    ('Revisa el archivo de configuración.', 3, 2, FALSE); -- Respuesta de Maria López al tópico 3
