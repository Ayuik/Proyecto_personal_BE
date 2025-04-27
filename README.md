# 🎶 Portfolio  - Backend
Welcome to my final individual project for the [Factoría F5](https://www.factoriaf5.com/) bootcamp, where I'm training as a full-stack developer. 🚀 This is the backend of a portfolio-style website designed to enhance the digital presence of a music producer, session musician, and composer.

---

## 📖 Project Description  

The main goal of this project is to develop a **professional website** that serves several key functions:

- **Personalized Services**  
  - Detailed information about session musician and music production services.  
  - Contact system for inquiries and hiring.

- **Video Course Store**  
  - Space for selling video courses related to music and production.  
  - Secure payment gateway integration and features for downloading or streaming purchased courses.

- **Administrative Management**  
  - An admin panel to manage services, courses, and transactions.  

- **User Dashboard**  
  - A personalized area where users can access and manage their purchased courses, review materials, and track their learning progress.  

---

## 💻 Technical Scope  

This project is **full stack**. The **Back-End** has been implemented using a robust and scalable **JAVA** server with **Spring Boot**, utilizing Spring Data JPA, Spring Security, and JWT for business logic and user authentication.

---

## 🛠️ Technologies and Dependencies  

The project uses the following technologies:

- **Spring Boot** (v3.4.4)  
- **Java 21**  
- **Spring Data JPA**  
- **Spring Security and OAuth2 Resource Server**  
- **JWT (JSON Web Tokens)** for authentication  
- **H2 Database** for development and testing  
- **Maven** as a dependency management tool  

Key dependencies included in `pom.xml`:  
- `spring-boot-starter-data-jpa`  
- `spring-boot-starter-security`  
- `spring-boot-starter-web`  
- `io.jsonwebtoken` (various versions for working with JWT)  
- `spring-boot-devtools` and `spring-boot-starter-test` for development and testing  

---

## 🚀 Setup and Execution  

### Prerequisites  

- [JDK 21](https://jdk.java.net/21/) or later  
- [Maven](https://maven.apache.org/)  
- Environment variable configuration with:  
  - JWT key (`jwt.key`)  

### Steps to run the project  

1. **Clone the repository:**  

    ```bash
    git clone https://github.com/Ayuik/Proyecto_personal_BE.git
    cd Proyecto_personal_BE
    ```

2. **Compile and package with Maven:**  

    ```bash
    mvn clean install
    ```

3. **Run the application:**  

    ```bash
    mvn spring-boot:run
    ```

   The application will run on the configured port (default: `8080`).  

---

## 📚 Key Features  

- **Authentication and Security:**  
  Implementation of Spring Security and JWT to protect endpoints and manage user authentication.

- **Content Management:**  
  CRUD operations for users, roles, categories, courses, and videos.  
  Use of JPA relationships with `CascadeType.ALL` and `orphanRemoval` to manage entity persistence.

- **Access Control and Administration:**  
  Security filters configured to differentiate public and admin endpoints.

---

## 🤝 Contributions  

All contributions are welcome! If you find any issues or have improvement suggestions, please open an *issue* or a *pull request* in this repository.  

---

## ⭐️ License  

This project is open-source. Feel free to use, modify, and distribute it under the applicable license terms!  

---

Thanks for visiting **Portfolio** and for your interest in this project! 🎵✨  
