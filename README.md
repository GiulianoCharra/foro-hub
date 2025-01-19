### README: Foro Hub API - Oracle ONE Challenge

---

# Foro Hub API

**Foro Hub** es una API RESTful creada como parte del desafío backend de [Oracle ONE](https://www.oracle.com)
y [Alura](https://www.aluracursos.com/). Este proyecto replica un foro donde los usuarios pueden crear, listar,
actualizar y eliminar tópicos, además de interactuar con respuestas asociadas.

El objetivo principal de este desafío es aprender y aplicar conceptos como:

- Principios de diseño SOLID.
- Patrones de diseño como Builder y Strategy.
- Integración de bases de datos con JPA/Hibernate.
- Seguridad y autenticación mediante **JWT**.
- Validación de datos con Bean Validation.
- Documentación con Swagger/OpenAPI.

---

## Funcionalidades

1. **Gestión de tópicos (CRUD):**
    - Crear un nuevo tópico.
    - Listar todos los tópicos con soporte de paginación.
    - Consultar detalles de un tópico específico.
    - Actualizar un tópico existente.
    - Eliminar un tópico.

2. **Autenticación y Autorización:**
    - Registro de usuarios.
    - Inicio de sesión con generación de **JWT**.
    - Validación de JWT para proteger los endpoints.

3. **Gestión de relaciones:**
    - Los tópicos están vinculados a usuarios (autores) y cursos.
    - Los usuarios pueden tener múltiples perfiles (roles).

4. **Documentación interactiva:**
    - Exploración de endpoints mediante Swagger UI.

5. **Seguridad:**
    - Autenticación basada en token JWT.
    - Protección de endpoints con Spring Security.

---

## Tecnologías Usadas

- **Lenguaje:** Java 21
- **Framework:** Spring Boot 3.4.1
- **Base de Datos:** MySQL 8.0
- **ORM:** Hibernate (JPA)
- **Seguridad:** Spring Security + JWT
- **Gestión de dependencias:** Maven
- **Documentación:** SpringDoc OpenAPI 3 (Swagger)

---

## Estructura del Proyecto

El proyecto sigue una arquitectura en capas que facilita la separación de responsabilidades:

```
src
├── main
│   ├── java
│   │   └── oracle.alura.challenge.forohub
│   │       ├── application
│   │       │   ├── controller       # Controladores REST
│   │       │   ├── dto              # Clases DTO para request/response
│   │       │   ├── exception        # Excepciones personalizadas
│   │       │   └── service          # Lógica de negocio
│   │       ├── config               # Configuración de seguridad, JWT, Swagger, etc.
│   │       ├── domain
│   │       │   ├── entity           # Entidades JPA
│   │       │   └── repository       # Interfaces de repositorios
│   │       └── ForoHubApplication   # Punto de entrada de la aplicación
│   ├── resources
│   │   ├── db.migration             # Scripts de Flyway para la creación de la base de datos
│   │   ├── application.properties   # Configuración de la aplicación
│   │   └── static                   # Archivos estáticos (Swagger UI)
└── test                             # Tests unitarios y de integración
```

---

## Instalación y Ejecución

### Prerrequisitos

1. **Java 21** instalado en tu máquina.
2. **Maven 3.8** o superior.
3. **MySQL 8.0** corriendo en tu máquina o en Docker.

### Configuración de la Base de Datos

1. Crea la base de datos ejecutando el script en `src/main/resources/db.migration/V1__create_schema.sql`.
2. Configura la conexión en `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/foro_hub
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   ```

### Instalación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/foro-hub.git
   cd foro-hub
   ```
2. Instala las dependencias:
   ```bash
   mvn clean install
   ```

### Ejecución

1. Inicia la aplicación:
   ```bash
   mvn spring-boot:run
   ```
2. Accede a la API en: [http://localhost:8080](http://localhost:8080)

---

## Uso de la API

### Endpoints principales

| Método | URI                 | Descripción                         | Autenticación |
|--------|---------------------|-------------------------------------|---------------|
| POST   | `/api/auth/signup`  | Registro de usuario                 | No            |
| POST   | `/api/auth/login`   | Inicio de sesión y obtención de JWT | No            |
| GET    | `/api/topicos`      | Listar todos los tópicos            | Sí            |
| GET    | `/api/topicos/{id}` | Obtener detalles de un tópico       | Sí            |
| POST   | `/api/topicos`      | Crear un nuevo tópico               | Sí            |
| PUT    | `/api/topicos/{id}` | Actualizar un tópico existente      | Sí            |
| DELETE | `/api/topicos/{id}` | Eliminar un tópico                  | Sí            |

---

### Autenticación con JWT

1. Usa `/api/auth/login` para obtener el token JWT.
2. Incluye el token en el encabezado `Authorization` de cada solicitud:
   ``` json json lines
   "Authorization": Bearer <tu_token>
   ```

---

## Documentación Swagger

Accede a la documentación interactiva en:
[http://localhost:8080/api/swagger-ui/index.html](http://localhost:8080/api/swagger-ui/index.html)

---

## Contribuciones

Las contribuciones son bienvenidas. Por favor:

1. Haz un fork del repositorio.
2. Crea una rama para tu funcionalidad:
   ```bash
   git checkout -b nueva-funcionalidad
   ```
3. Haz un pull request explicando tu contribución.

---

## Licencia

Este proyecto está bajo la Licencia Apache 2.0. Ver el archivo `LICENSE` para más detalles.

---

¡Gracias por explorar **Foro Hub**! 🎉