# 🚆 Train Booking System - Backend

A RESTful backend application for a **Segment-Based Train Booking System** developed using **Spring Boot** and **PostgreSQL**. The system provides APIs for managing train stations, train schedules, users, and seat bookings while supporting segment-based seat availability.

---

## Features

- User registration and management
- Station management (CRUD)
- Train schedule management (CRUD)
- Segment-based seat availability
- Seat booking
- Booking validation
- Global exception handling
- RESTful API architecture

---

## Tech Stack

- Java 17
- Spring Boot 4
- Spring Web
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok

---

## Project Structure

```
src
├── controller
├── service
│   └── impl
├── repository
├── model
├── dto
├── exception
└── TrainbookingsystemApplication.java
```

---

## API Endpoints

### Users

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/api/users` | Register a new user |
| GET | `/api/users/{id}` | Get user by ID |
| PUT | `/api/users/{id}` | Update user |

---

### Stations

| Method | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/stations` | Get all stations |
| POST | `/api/stations` | Create station |
| PUT | `/api/stations/{id}` | Update station |
| DELETE | `/api/stations/{id}` | Delete station |

---

### Train Schedules

| Method | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/schedules` | Get all schedules |
| GET | `/api/schedules/{id}` | Get schedule by ID |
| POST | `/api/schedules` | Create schedule |
| PUT | `/api/schedules/{id}` | Update schedule |
| DELETE | `/api/schedules/{id}` | Delete schedule |

---

### Bookings

| Method | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/bookings/available` | View available seats |
| POST | `/api/bookings` | Book a seat |

---

## Database

The application uses **PostgreSQL** as the primary database.

Update the following properties in `application.properties` before running:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/train_booking
spring.datasource.username=your_username
spring.datasource.password=your_password
```

---

## Running the Project

Clone the repository

```bash
git clone https://github.com/mjKas/Train_Booking_System.git
```

Navigate into the project

```bash
cd Train_Booking_System
```

Run the application

```bash
./mvnw spring-boot:run
```

or

```bash
mvn spring-boot:run
```

The server starts on

```
http://localhost:8080
```

---

## Architecture

```
Client
   │
   ▼
REST Controllers
   │
   ▼
Service Layer
   │
   ▼
Repositories
   │
   ▼
PostgreSQL Database
```

---

## Future Improvements

- JWT Authentication
- User Login
- Role-based Authorization
- Online Payment Integration
- Booking History
- Train Search by Source, Destination and Date
- Email Notifications
- Frontend using React

---

## Author

**Manuja Kasun**

Bachelor of Engineering (Software Engineering)

Staffordshire University, UK
