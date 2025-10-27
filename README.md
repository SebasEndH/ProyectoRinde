# Proyecto Rinde

`ProyectoRinde` es una aplicación web para la gestión de la productividad desarrollada con Spring Boot y Thymeleaf. El objetivo principal de la aplicación es ayudar a los usuarios a administrar sus tareas diarias implementando la **Técnica Pomodoro**.

La aplicación permite a los usuarios registrarse, iniciar sesión, gestionar una lista de tareas pendientes y utilizar un temporizador Pomodoro integrado para intercalar sesiones de trabajo enfocado con descansos programados.

## Características Principales

* **Autenticación de Usuarios:** Sistema completo de registro e inicio de sesión (con contraseñas encriptadas usando BCrypt).
* **Gestión de Tareas:** Los usuarios autenticados pueden crear, ver y eliminar tareas. Cada tarea está vinculada a la cuenta del usuario.
* **Temporizador Pomodoro:** Una herramienta de temporizador (25 min de trabajo / 5 min de descanso) en una página dedicada para gestionar las sesiones de estudio o trabajo.
* **Páginas Web Dinámicas:** Interfaz de usuario renderizada del lado del servidor usando Thymeleaf.
* **Música de Fondo:** Un reproductor de YouTube integrado en la barra de navegación para seleccionar y reproducir música ambiental (Blues, Jazz, Clásica) durante las sesiones.

##  Stack Tecnológico

* **Backend:** Java 21, Spring Boot 3.5.6
* **Frameworks:** Spring Web, Spring Data JPA, Spring Security (para codificación de contraseñas)
* **Frontend:** Thymeleaf (Renderizado del lado del servidor), Bootstrap 5, JavaScript
* **Base de Datos:** MySQL Connector (Configurada para MySQL)
* **Utilidades:** Lombok, Maven (Wrapper)

##  Configuración y Prerrequisitos

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

##  Ejecución

Una vez configurada la base de datos, puedes iniciar la aplicación usando el Maven Wrapper incluido en el proyecto.

**En Windows:**

```bash
# Ejecutar el proyecto
./mvnw.cmd spring-boot:run
```


La aplicación estará disponible en http://localhost:8080.