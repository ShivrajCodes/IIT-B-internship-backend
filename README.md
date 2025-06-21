# 📘 Courses Management System — Internship Assignment (IIT Bombay ASC)

This project contains both the backend and frontend applications for managing university courses and their delivery instances. Developed as part of the internship application for **Application Software Centre, IIT Bombay**.

---

## 📦 Contents

- ✅ Backend — Spring Boot REST API
- 💻 Frontend — ReactJS Web Application
- 🐳 Dockerized Deployment (with GitHub Actions & DockerHub)
- 🧩 docker-compose Setup
- 📑 API Design and Justification

---

## ✅ Backend: Courses API (Spring Boot)

A Spring Boot application exposing RESTful APIs for managing courses and their instances.

### 🔧 Technologies

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- H2 / PostgreSQL
- Docker & DockerHub
- GitHub Actions

### 📌 API Endpoints

#### 📚 Courses

| Method | Endpoint               | Description                                 |
|--------|------------------------|---------------------------------------------|
| POST   | `/api/courses`         | Create a new course (validates prerequisites) |
| GET    | `/api/courses`         | List all courses (with prerequisites)       |
| GET    | `/api/courses/{id}`    | Get details of a specific course            |
| DELETE | `/api/courses/{id}`    | Delete a course if no dependency exists     |

> ⚠️ A course **cannot be deleted** if it is a prerequisite for another course. Returns `409 Conflict`.

#### 📆 Course Delivery Instances

| Method | Endpoint                                | Description                                |
|--------|------------------------------------------|--------------------------------------------|
| POST   | `/api/instances`                         | Create a new course delivery instance      |
| GET    | `/api/instances/{year}/{semester}`       | List instances by year & semester          |
| GET    | `/api/instances/{year}/{semester}/{id}`  | Get specific course instance details       |
| DELETE | `/api/instances/{year}/{semester}/{id}`  | Delete course instance                     |

---

### 🐳 Backend Docker Commands

```bash
docker build -t <your-dockerhub-username>/courses-api .
docker push <your-dockerhub-username>/courses-api
