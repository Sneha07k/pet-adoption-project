# 🐾 Pet Adoption / Pet Finder — Spring Boot REST API

A clean, production-ready RESTful backend for a Pet Adoption web application built with **Java 17**, **Spring Boot 3**, **Spring Data JPA**, and **MySQL**.

---

## 📁 Project Structure

```
pet-adoption/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/com/petadoption/
    │   │   ├── PetAdoptionApplication.java          ← Main entry point
    │   │   │
    │   │   ├── controller/
    │   │   │   └── PetController.java               ← REST endpoints
    │   │   │
    │   │   ├── service/
    │   │   │   ├── PetService.java                  ← Service interface
    │   │   │   └── PetServiceImpl.java              ← Business logic
    │   │   │
    │   │   ├── repository/
    │   │   │   └── PetRepository.java               ← JPA repository
    │   │   │
    │   │   ├── model/
    │   │   │   └── Pet.java                         ← JPA entity
    │   │   │
    │   │   ├── dto/
    │   │   │   ├── PetRequest.java                  ← Inbound DTO
    │   │   │   ├── PetResponse.java                 ← Outbound DTO
    │   │   │   └── ApiResponse.java                 ← Generic wrapper
    │   │   │
    │   │   ├── exception/
    │   │   │   ├── ResourceNotFoundException.java   ← Custom 404 exception
    │   │   │   ├── ErrorDetails.java                ← Error response model
    │   │   │   └── GlobalExceptionHandler.java      ← @ControllerAdvice
    │   │   │
    │   │   └── config/
    │   │       ├── PetMapper.java                   ← Entity ↔ DTO mapping
    │   │       └── DataSeeder.java                  ← Sample data on startup
    │   │
    │   └── resources/
    │       ├── application.properties               ← App configuration
    │       └── schema.sql                           ← Manual DB setup script
    │
    └── test/
        └── java/com/petadoption/
            └── PetServiceImplTest.java              ← Unit tests
```

---

## ⚙️ Prerequisites

| Tool      | Version     |
|-----------|-------------|
| Java      | 17 or higher|
| Maven     | 3.8+        |
| MySQL     | 8.0+        |
| Postman   | Any version |

---

## 🗄️ Database Setup

### Step 1 — Create the MySQL Database

Open MySQL Workbench or your terminal and run:

```sql
CREATE DATABASE pet_adoption_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;
```

### Step 2 — Configure Credentials

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/pet_adoption_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_password_here
```

Replace `root` and `your_password_here` with your actual MySQL credentials.

> **Note:** Hibernate is configured with `ddl-auto=update`, so it will **automatically create/update the `pets` table** on first startup. No manual SQL required.

---

## 🚀 Running the Application

### Option A — Using Maven Wrapper (recommended)

```bash
cd pet-adoption
./mvnw spring-boot:run
```

### Option B — Using Maven directly

```bash
mvn spring-boot:run
```

### Option C — Build JAR and run

```bash
mvn clean package -DskipTests
java -jar target/pet-adoption-1.0.0.jar
```

The application starts on **http://localhost:8080**

On first run, the `DataSeeder` automatically inserts **6 sample pets** into the database.

---

## 📡 API Endpoints

### Base URL: `http://localhost:8080/api/pets`

| Method   | Endpoint                          | Description                    |
|----------|-----------------------------------|--------------------------------|
| `POST`   | `/api/pets`                       | Add a new pet for adoption     |
| `GET`    | `/api/pets`                       | Get all pets                   |
| `GET`    | `/api/pets/{id}`                  | Get a specific pet by ID       |
| `PUT`    | `/api/pets/{id}`                  | Update pet details             |
| `DELETE` | `/api/pets/{id}`                  | Delete a pet listing           |
| `GET`    | `/api/pets/type/{type}`           | Filter pets by type            |
| `GET`    | `/api/pets/location/{location}`   | Filter pets by location        |
| `GET`    | `/api/pets/age/younger-than/{age}`| Get pets younger than given age|

---

## 🧪 Testing with Postman

### 1. Add a New Pet — `POST /api/pets`

**URL:** `http://localhost:8080/api/pets`
**Method:** POST
**Headers:** `Content-Type: application/json`
**Body (raw JSON):**

```json
{
  "name": "Rocky",
  "type": "Dog",
  "breed": "Labrador",
  "age": 1,
  "description": "Rocky is a playful pup looking for a loving home.",
  "location": "Austin, TX",
  "ownerName": "Alice Cooper",
  "ownerContact": "alice.cooper@email.com"
}
```

**Expected Response (201 Created):**

```json
{
  "success": true,
  "message": "Pet added successfully",
  "data": {
    "id": 7,
    "name": "Rocky",
    "type": "Dog",
    "breed": "Labrador",
    "age": 1,
    "description": "Rocky is a playful pup looking for a loving home.",
    "location": "Austin, TX",
    "ownerName": "Alice Cooper",
    "ownerContact": "alice.cooper@email.com",
    "createdAt": "2024-01-15T10:30:00"
  },
  "timestamp": "2024-01-15T10:30:00"
}
```

---

### 2. Get All Pets — `GET /api/pets`

**URL:** `http://localhost:8080/api/pets`
**Method:** GET

Returns all pet listings as a JSON array.

---

### 3. Get Pet by ID — `GET /api/pets/{id}`

**URL:** `http://localhost:8080/api/pets/1`
**Method:** GET

---

### 4. Update Pet — `PUT /api/pets/{id}`

**URL:** `http://localhost:8080/api/pets/1`
**Method:** PUT
**Headers:** `Content-Type: application/json`
**Body:**

```json
{
  "name": "Buddy",
  "type": "Dog",
  "breed": "Golden Retriever",
  "age": 3,
  "description": "Updated description — Buddy is now 3 years old!",
  "location": "Brooklyn, NY",
  "ownerName": "John Smith",
  "ownerContact": "john.smith@email.com"
}
```

---

### 5. Delete Pet — `DELETE /api/pets/{id}`

**URL:** `http://localhost:8080/api/pets/7`
**Method:** DELETE

---

### 6. Filter by Type — `GET /api/pets/type/{type}`

**URL:** `http://localhost:8080/api/pets/type/Dog`
**Method:** GET

---

### 7. Filter by Location — `GET /api/pets/location/{location}`

**URL:** `http://localhost:8080/api/pets/location/New York`
**Method:** GET

---

### 8. Filter by Age — `GET /api/pets/age/younger-than/{age}`

**URL:** `http://localhost:8080/api/pets/age/younger-than/3`
**Method:** GET

---

## ❌ Error Handling Examples

### Pet Not Found (404)

```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Pet not found with id: 999",
  "path": "/api/pets/999"
}
```

### Validation Error (400)

```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 400,
  "error": "Validation Failed",
  "message": "One or more fields are invalid",
  "path": "/api/pets",
  "validationErrors": {
    "name": "Pet name is required",
    "location": "Location is required"
  }
}
```

---

## 🧪 Running Unit Tests

```bash
mvn test
```

---

## 🏗️ Architecture Overview

```
HTTP Request
    │
    ▼
PetController          ← Handles routing, request parsing, response formatting
    │
    ▼
PetService (interface) ← Defines the contract
PetServiceImpl         ← Business logic, transactions, exception handling
    │
    ▼
PetRepository          ← Spring Data JPA — database queries
    │
    ▼
Pet (entity)           ← JPA-mapped table
    │
    ▼
MySQL Database
```

**Supporting Components:**
- `PetMapper` — Entity ↔ DTO conversion
- `PetRequest / PetResponse / ApiResponse` — DTO layer
- `GlobalExceptionHandler` — Centralized error handling
- `DataSeeder` — Sample data on startup

---

## 🔧 Technologies Used

| Technology            | Purpose                          |
|-----------------------|----------------------------------|
| Java 17               | Core language                    |
| Spring Boot 3.2       | Application framework            |
| Spring Data JPA       | Database abstraction layer       |
| Hibernate             | ORM implementation               |
| MySQL 8               | Relational database              |
| Lombok                | Boilerplate code reduction       |
| Bean Validation (JSR-380) | Input validation            |
| Maven                 | Build & dependency management    |
| JUnit 5 + Mockito     | Unit testing                     |
