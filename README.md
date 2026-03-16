# 🎓 Scholarship Management System (SMS)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

A robust and scalable Backend API designed to streamline the process of managing student scholarships. 
This system handles everything from student registrations to scholarship applications and automated ranking.

## 🌟 Key Features

* **User Authentication**: Secure Login & Registration using **JWT (JSON Web Tokens)**.
* **Role-Based Access**: Specialized endpoints for Students and Administrators.
* **Student Management**: Full CRUD operations for student profiles.
* **Scholarship Workflow**: Manage scholarship calls, applications, and status tracking.
* **Automated Ranking**: Logic for ranking students based on specific criteria.
* **Documentation**: Fully interactive API documentation.

## 🛠️ Technology Stack

* **Backend**: Java & Spring Boot
* **Security**: Spring Security & JWT
* **Database**: PostgreSQL
* **Containerization**: Docker & Docker Compose
* **Documentation**: Swagger UI & OpenAPI 3
* **Build Tool**: Maven

---

## 🚀 Getting Started (Docker Run)

The entire environment is containerized. You don't need to have Java or PostgreSQL installed on your machine—only **Docker Desktop**.

### 1. Clone the repository
```bash
git clone [https://github.com/Kenciii/stipendije.git](https://github.com/Kenciii/stipendije.git)
cd stipendije
```

### 2. Launch with Docker Compose
To build the application and start the services, run:
```bash
docker-compose up --build
```
This command will compile the code, create a Docker image for the backend, pull the PostgreSQL image, and link them together.


### 3. Verify the installation
Once the logs show that Spring Boot has started, you can access the services:

Backend API: http://localhost:8080

Database: localhost:5432 (PostgreSQL)


# 📖 API Documentation & Testing

## 🟢 Swagger UI

Explore and test all API endpoints directly through the browser:
🔗 http://localhost:8080/swagger-ui/index.html

How to test protected routes:

Use the /api/auth/register endpoint to create an account.

Use the /api/auth/login endpoint to get your Bearer Token.

Click the "Authorize" button in Swagger and paste your token.


## 🟠 Postman & OpenAPI Assets

For a more detailed testing experience, navigate to the /docs folder:

Postman Collection: Import this into Postman for ready-to-use requests.

OpenAPI Specification: Standard JSON definition of the API.


## 📁 Project Structure

src/ - Application source code.

docs/ - Documentation assets (Postman files, OpenAPI JSON).

Dockerfile - Multi-stage build configuration for the Java application.

docker-compose.yml - Orchestration for the App and PostgreSQL containers.

.gitignore - Standard Java/IntelliJ/Docker ignore rules.
