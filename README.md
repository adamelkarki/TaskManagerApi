
# Task Manager API

Backend REST API for managing professional tasks, built with **Java 17+** and **Spring Boot**, following clean architecture and enterprise best practices.

This project is designed as a **learning project with production-grade standards**, focusing on:
- clean code
- clear separation of concerns
- strong understanding of persistence and architecture

---

## 🚀 Features

- Create, read, update and delete tasks
- Task lifecycle management (status, priority, due date)
- UUID-based identifiers
- Automatic timestamps (`created_at`, `updated_at`)
- Strong domain modeling with enums
- Ready for database migrations (Flyway)
- Designed for testability and maintainability

---

## 🧱 Tech Stack

- **Java 17+**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **Hibernate**
- **PostgreSQL**
- **Flyway**
- **Maven**

---

## 📐 Architecture

The project follows a layered architecture:

com.example.taskmanagerapi
│
├── domain
│   └── model        # JPA entities and domain enums
│
├── repository       # Spring Data JPA repositories
│
├── service          # Business logic (domain rules)
│
├── controller       # REST API layer
│
├── dto              # API request / response models
│
└── config           # Application configuration


### Design principles applied

- Controllers contain **no business logic**
- Services contain **all domain rules**
- Repositories handle **persistence only**
- Entities are **not exposed** through the API
- Strong separation between domain and infrastructure

---

## 🗄 Database Model

### `task` table

| Column       | Type         | Description |
|--------------|--------------|-------------|
| id           | UUID         | Primary key |
| title        | VARCHAR(100) | Task title |
| description  | VARCHAR(500) | Optional description |
| status       | VARCHAR(20)  | Task status (enum) |
| priority     | VARCHAR(20)  | Task priority (enum) |
| due_date     | DATE         | Due date |
| created_at  | TIMESTAMP    | Creation timestamp |
| updated_at  | TIMESTAMP    | Last update timestamp |

---

## 🧪 Persistence Strategy

- **Hibernate** handles object–relational mapping
- **Flyway** is responsible for database schema evolution
- Hibernate is **not allowed** to create or modify schemas in production
- Database schema is versioned, reproducible, and environment-safe

---

## 🧼 Clean Code & Best Practices

- Explicit naming (no `Utils`, no ambiguous classes)
- Small, focused classes and methods
- One responsibility per layer
- UUIDs instead of incremental IDs
- No lazy loading in controllers
- Business rules enforced in services
- Commits follow clear, intention-revealing conventions

---

## 🛠 How to Run

### Prerequisites

- Java 17+
- PostgreSQL
- Maven

## 📌 Project Status

This project is **actively developed** as part of a structured learning path focused on:
- mastering Java & Spring Boot
- preparing for freelance backend missions
- building production-ready codebases

---

## 📄 License

This project is for educational and demonstration purposes.

