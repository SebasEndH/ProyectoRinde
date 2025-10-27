# Proyecto Rinde

`ProyectoRinde` es una aplicación web para la gestión de la productividad desarrollada con Spring Boot y Thymeleaf. El objetivo principal de la aplicación es ayudar a los usuarios a administrar sus tareas diarias implementando la **Técnica Pomodoro**.

La aplicación permite a los usuarios registrarse, iniciar sesión, gestionar una lista de tareas pendientes y utilizar un temporizador Pomodoro integrado para intercalar sesiones de trabajo enfocado con descansos programados.

## 1. Características Principales

* **Autenticación de Usuarios:** Sistema completo de registro e inicio de sesión (con contraseñas encriptadas usando BCrypt).
* **Gestión de Tareas:** Los usuarios autenticados pueden crear, ver y eliminar tareas. Cada tarea está vinculada a la cuenta del usuario.
* **Temporizador Pomodoro:** Una herramienta de temporizador (25 min de trabajo / 5 min de descanso) en una página dedicada para gestionar las sesiones de estudio o trabajo.
* **Páginas Web Dinámicas:** Interfaz de usuario renderizada del lado del servidor usando Thymeleaf.
* **Música de Fondo:** Un reproductor de YouTube integrado en la barra de navegación para seleccionar y reproducir música ambiental (Blues, Jazz, Clásica) durante las sesiones.

## 2. Stack Tecnológico

* **Backend:** Java 21, Spring Boot 3.5.6
* **Frameworks:** Spring Web, Spring Data JPA, Spring Security (para codificación de contraseñas)
* **Frontend:** Thymeleaf (Renderizado del lado del servidor), Bootstrap 5, JavaScript
* **Base de Datos:** MySQL Connector (Configurada para MySQL)
* **Utilidades:** Lombok, Maven (Wrapper)

## 3. Configuración y Prerrequisitos

Antes de ejecutar el proyecto, necesitarás:

1.  **Java JDK 21** o superior.
2.  Un servidor **MySQL** en ejecución.
3.  (Opcional) Maven instalado, aunque se recomienda usar el wrapper incluido (`mvnw`).

### 1. Configuración de la Base de Datos

El proyecto está configurado para conectarse a una base de datos MySQL local.

1.  Asegúrate de que tu servidor MySQL esté activo.
2.  La aplicación intentará conectarse a la base de datos `proy_rinde_bd`. Gracias a la configuración `createDatabaseIfNotExist=true`, no necesitas crearla manualmente.
3.  Ajusta el usuario y la contraseña en `src/main/resources/application.properties` si son diferentes de los valores por defecto:

    ```properties
    # Configuracion de base de datos MySQL
    spring.datasource.url=jdbc:mysql://localhost:3306/proy_rinde_bd?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
    spring.datasource.username=root
    spring.datasource.password=12345678
    ```


4.  La propiedad `spring.jpa.hibernate.ddl-auto=update` se encargará de crear las tablas (`Usuario`, `Tarea`) automáticamente la primera vez que se inicie la aplicación.

## 4. Ejecución

Una vez configurada la base de datos, puedes iniciar la aplicación usando el Maven Wrapper incluido en el proyecto.

**En Windows:**

```bash
# Ejecutar el proyecto
./mvnw.cmd spring-boot:run
```


La aplicación estará disponible en http://localhost:8080.

## 5. Endpoints de la Aplicación

La aplicación expone las siguientes rutas principales:

### `PrincipalController`
* `GET /` (o `/index`): Muestra la página de inicio.
* `GET /herramienta/pomodoro`: Muestra la página con el temporizador Pomodoro.

### `UsuarioController`
* `POST /login`: Procesa el formulario de inicio de sesión.
    * **Parámetros (form):** `email`, `password`.
    * **Redirige a:** `/` (con sesión) o `/` (con error).
* `POST /registro`: Procesa el formulario de registro.
    * **Parámetros (form):** `nombre`, `email`, `password`.
    * **Redirige a:** `/` (o `/` con error de registro).
* `GET /logout`: Cierra la sesión del usuario.
    * **Redirige a:** `/`.

### `TareaController` (Requiere inicio de sesión)
* `GET /tareas`: Muestra la página de gestión de tareas del usuario.
* `POST /tareas`: Procesa el formulario para agregar una nueva tarea.
    * **Parámetros (form):** `nombre`, `descripcion` (opcional).
    * **Redirige a:** `/tareas`.
* `POST /tareas/eliminar`: Elimina una tarea específica.
    * **Parámetros (form):** `nombre`.
    * **Redirige a:** `/tareas`.

## 6. Arquitectura del Proyecto (MVC)

Este proyecto está estructurado siguiendo el patrón arquitectónico **Modelo-Vista-Controlador (MVC)**, que separa las responsabilidades de la aplicación en tres capas principales:

### Modelo (Model)

Representa la capa de datos y la lógica de negocio. Es responsable de manejar la información, validarla y persistirla en la base de datos. En este proyecto, la capa de Modelo se compone de:

* **Entidades (Model):** Definen la estructura de los datos (POJOs mapeados a la BD con JPA).
    * `src/main/java/com/example/ProyectoRinde/Model/Usuario.java`
    * `src/main/java/com/example/ProyectoRinde/Model/Tarea.java`
* **Repositorios (Repository):** Interfaces de Spring Data JPA que definen el acceso a los datos (consultas CRUD).
    * `src/main/java/com/example/ProyectoRinde/Repository/UsuarioRepository.java`
    * `src/main/java/com/example/ProyectoRinde/Repository/TareaRepository.java`
* **Servicios (Service):** Clases que contienen la lógica de negocio (ej. cómo registrar un usuario, cómo agregar una tarea).
    * `src/main/java/com/example/ProyectoRinde/Service/UsuarioService.java`
    * `src/main/java/com/example/ProyectoRinde/Service/TareaService.java`
* **DTOs y Validadores:** Clases para la transferencia de datos y validaciones personalizadas.
    * `src/main/java/com/example/ProyectoRinde/DTO/`
    * `src/main/java/com/example/ProyectoRinde/Validator/`

### Vista (View)

Representa la capa de presentación (la interfaz de usuario). En este proyecto, se implementa mediante plantillas **Thymeleaf** que se renderizan en el servidor, junto con los archivos estáticos (CSS, JS).

* **Plantillas (templates):** Ubicadas en `src/main/resources/templates/`.
    * `index.html` (Página de inicio)
    * `herramienta/pomodoro.html` (Herramienta Pomodoro)
    * `tareas/tareas.html` (Página de gestión de tareas)
    * `fragments/navbar.html` (Componente de navegación reutilizable)
* **Recursos Estáticos (static):** Ubicados en `src/main/resources/static/`.
    * `CSS/`: Hojas de estilo
    * `JS/`: Lógica del lado del cliente (Pomodoro, Navbar, YouTube)
    * `IMG/`: Imágenes e iconos.

### Controlador (Controller)

Actúa como el intermediario entre el Modelo y la Vista. Recibe las peticiones HTTP (del navegador), llama a los Servicios correspondientes para procesar la lógica de negocio y finalmente devuelve una Vista (plantilla Thymeleaf) con los datos necesarios para mostrar al usuario.

* **Ubicación:** `src/main/java/com/example/ProyectoRinde/Controller/`
* **Controladores principales:**
    * `PrincipalController.java`: Maneja las rutas estáticas como la página de inicio (`/`) y la herramienta pomodoro (`/herramienta/pomodoro`).
    * `UsuarioController.java`: Maneja las peticiones de autenticación: `/login`, `/logout` y `/registro`.
    * `TareaController.java`: Maneja el CRUD de tareas (ver, agregar, eliminar) en las rutas `/tareas` y `/tareas/eliminar`.