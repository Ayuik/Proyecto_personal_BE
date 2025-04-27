# 🎶 Portfolio
 Bienvenidxs a mi proyecto final individual para el bootcamp de [Factoría F5](https://www.factoriaf5.com/) en el que me formo como desarrolladora full stack. 🚀 Este es el backend de un sitio web estilo portfolio diseñado para fortalecer la presencia digital de un productor musical, músico sesionista y compositor.

---

## 📖 Descripción del Proyecto

El objetivo principal de este proyecto es desarrollar una **página web profesional** que cumpla varias funciones clave:

- **Servicios Personalizados**  
  - Información detallada sobre los servicios de músico sesionista y productor musical.  
  - Sistema de contacto para consultas y contratación.

- **Tienda de Cursos en Video**  
  - Espacio para la venta de cursos en formato video relacionados con música y producción musical.  
  - Integración de pasarelas de pago seguras y funcionalidades para descargas o streaming de cursos adquiridos.

- **Gestión Administrativa**  
  - Panel de administración para gestionar servicios, cursos y transacciones realizadas.
  
- **Panel de Usuario**  
  - Área personalizada donde los usuarios pueden acceder y gestionar el contenido de los cursos comprados, consultando sus materiales y realizando el seguimiento de su aprendizaje.
---

## 💻 Alcance Técnico

Este proyecto es full stack. En **Back-End** se ha implementado un servidor robusto y escalable en **JAVA** con **Spring Boot**, utilizando Spring Data JPA, Spring Security y JWT para gestionar la lógica de negocio y la autenticación de usuarios.

---

## 🛠️ Tecnologías y Dependencias

El proyecto utiliza las siguientes tecnologías:

- **Spring Boot** (v3.4.4)  
- **Java 21**  
- **Spring Data JPA**  
- **Spring Security y OAuth2 Resource Server**  
- **JWT (JSON Web Tokens)** para autenticación  
- **H2 Database** para desarrollo y testing  
- **Maven** como herramienta de gestión de dependencias  

Entre las dependencias destacadas en el `pom.xml` tenemos:
- `spring-boot-starter-data-jpa`
- `spring-boot-starter-security`
- `spring-boot-starter-web`
- `io.jsonwebtoken` (varias versiones para trabajar con JWT)
- `spring-boot-devtools` y `spring-boot-starter-test` para desarrollo y pruebas

---

## 🚀 Configuración y Ejecución

### Prerrequisitos

- [JDK 21](https://jdk.java.net/21/) o superior
- [Maven](https://maven.apache.org/)
- Configuración en variables de entorno con:
  - Llave para JWT (`jwt.key`)
  
### Pasos para levantar el proyecto

1. **Clonar el repositorio:**

    ```bash
    git clone https://github.com/Ayuik/Proyecto_personal_BE.git
    cd Proyecto_personal_BE
    ```

2. **Compilar y empaquetar con Maven:**

    ```bash
    mvn clean install
    ```

3. **Ejecutar la aplicación:**

    ```bash
    mvn spring-boot:run
    ```

   La aplicación se ejecutará en el puerto configurado (por defecto, `8080`).

---

## 📚 Funcionalidades Clave

- **Autenticación y Seguridad:**  
  Implementación de Spring Security y JWT para proteger endpoints y gestionar la autenticación de usuarios.

- **Gestión de Contenido:**  
  CRUD para usuarios, roles, categorías, cursos y videos.  
  Uso de relaciones JPA con `CascadeType.ALL` y `orphanRemoval` para gestionar la persistencia de entidades relacionadas.

- **Control de Acceso y Administración:**  
  Filtros de seguridad configurados para diferenciar endpoints públicos y de administración.

---

## 🤝 Contribuciones

¡Todas las contribuciones son bienvenidas! Si encuentras algún error o tienes sugerencias de mejora, por favor abre un *issue* o un *pull request* en este repositorio.

---

## ⭐️ Licencia

Este proyecto es de código abierto. ¡Siéntete libre de usar, modificar y distribuir bajo los términos de la licencia que corresponda!

---

¡Gracias por visitar **Portfolio** y por tu interés en este proyecto! 🎵✨
