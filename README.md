# 🏡 Sweet Home - Plataforma Inmobiliaria

Plataforma integral para la gestión y visualización de un catálogo de propiedades inmobiliarias. Este proyecto está compuesto por un backend robusto en Spring Boot, un frontend dinámico en Angular y una base de datos PostgreSQL, todo orquestado y contenedorizado para un despliegue transparente y sencillo.

## 🚀 Requisitos del Sistema

Gracias a la arquitectura basada en contenedores (Multi-stage builds), **el único requisito para ejecutar la aplicación completa es tener Docker instalado**. No es necesario tener Java, Node.js ni PostgreSQL instalados en tu equipo local.

* **Docker Desktop** (v24.0 o superior)
* **Docker Compose** (v2.0 o superior)
* *Nota para usuarios de Windows:* Docker Desktop requiere tener habilitado **WSL 2** (Windows Subsystem for Linux).

## ⚙️ Despliegue Rápido

El archivo `.env` con las variables de entorno (base de datos, puertos) ya se encuentra incluido en el repositorio para agilizar la evaluación de esta prueba técnica.

1. **Clonar el repositorio y entrar a la carpeta:**
   ```bash
   git clone https://github.com/Jony372/sweet_home.git
   cd sweet_home
   ```

2. **Levantar los servicios con Docker Compose:**
   Ejecuta el siguiente comando en tu terminal para construir y levantar la base de datos, el backend y el frontend en segundo plano:
   ```bash
   docker compose up --build -d
   ```
   *(Nota: La primera ejecución tomará unos minutos mientras Docker descarga las imágenes base, compila los proyectos internamente y genera los artefactos finales).*

## 🌐 Accesos y Documentación

Una vez que los contenedores estén en ejecución (`Up`), puedes acceder a la plataforma en los siguientes enlaces:

* 🖥️ **Frontend (Aplicación Web):** [http://localhost:4200](http://localhost:4200)
* 📖 **Documentación API (Swagger UI):** [http://localhost:8080/sweet-home/api/swagger-ui/index.html](http://localhost:8080/sweet-home/api/swagger-ui/index.html)
* 🗄️ **Base de Datos (PostgreSQL 18+):** `localhost:5432`

## 🔐 Credenciales de Prueba (Administrador)

Para evaluar el sistema de roles, modales restringidos y endpoints protegidos (creación de categorías y casas), inicia sesión en el Frontend con las siguientes credenciales:

* **Usuario:** `Admin`
* **Contraseña:** `Admin123`

## 🏗️ Arquitectura y Tecnologías

* **Frontend:** Angular 22, Tailwind CSS. Empaquetado y servido a través de Nginx.
* **Backend:** Spring Boot (Java 21), Spring Security con JWT, Gradle, OpenAPI/Swagger.
* **Base de Datos:** PostgreSQL 18+ (Diseño relacional normalizado en 3NF).
* **Infraestructura:** Docker & Docker Compose.