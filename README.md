# 🏨 Harborview — Hotel Booking & Management System

Harborview is a full-stack hotel booking and management system built with Java and Spring Boot. It provides separate customer and administrator workflows for hotel discovery, room management, reservations, payments, and booking lifecycle management.

## 🌐 Project Links

- **Live Demo:** Coming soon
- **Backend:** [Spring Boot Repository](https://github.com/vvaishnavii0609/Hotel-Management-System-SpringBoot)
- **Frontend:** [Frontend Repository](https://github.com/vvaishnavii0609/complete-frontend-hotelmanagement)
- **Demo Video:** Coming soon

## ✨ Features

### Customer
- User registration and login
- JWT-based authentication
- Hotel and room search
- Room availability
- Hotel booking
- Payment workflow
- Booking history
- Booking details
- Booking cancellation

### Administrator
- Secure administrator authentication
- Hotel management
- Room management
- View pending bookings
- Approve or reject bookings
- Manage hotel and room information

## 🔐 Authentication & Authorization

The application uses **Spring Security with JWT-based stateless authentication**.

- Users authenticate through the login API.
- A JWT is issued after successful authentication.
- Protected requests include the JWT using the `Authorization: Bearer <token>` header.
- Role-based authorization separates customer and administrator operations.
- Restricted APIs are protected through Spring Security.

```text
User Login
    ↓
JWT Generation
    ↓
Authenticated Request
    ↓
Spring Security
    ↓
Role Validation
    ↓
Authorized API Access
```

## 🔄 Booking Workflow

```text
Search Hotels
      ↓
Select Hotel
      ↓
Select Room
      ↓
Login / Register
      ↓
Create Booking
      ↓
Pending
      ↓
Admin Approval / Rejection
      ↓
Payment
      ↓
Booking Confirmation
      ↓
Booking History
```


## 🛠️ Tech Stack

### Backend
- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- JWT
- REST APIs
- MySQL

### Frontend
- HTML5
- CSS3
- JavaScript

### Testing
- JUnit
- Mockito

## 📡 API Modules

| Module | Operations |
|---|---|
| **User** | Registration, Login |
| **Hotel** | Search, Create, Update, Delete |
| **Room** | Search, Create, Update, Delete |
| **Booking** | Create, View, Cancel |
| **Admin Booking** | View Pending, Approve, Reject |
| **Payment** | Payment Processing |


## ⚙️ Getting Started

### Prerequisites

- Java 21
- Maven
- MySQL
- Git

### 1. Clone the Backend

```bash
git clone https://github.com/vvaishnavii0609/Hotel-Management-System-SpringBoot.git
cd Hotel-Management-System-SpringBoot
```

### 2. Configure MySQL

Create a MySQL database and configure the database connection in the Spring Boot application configuration.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hotel_management
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

**Do not commit database credentials or JWT secrets to the repository.**

### 3. Run the Backend

Linux/macOS:

```bash
./mvnw spring-boot:run
```

Windows:

```bash
mvnw.cmd spring-boot:run
```

### 4. Run the Frontend

Clone the frontend:

```bash
git clone https://github.com/vvaishnavii0609/complete-frontend-hotelmanagement.git
cd complete-frontend-hotelmanagement
```

Update the backend URL in `js/config.js`:

```javascript
window.APP_CONFIG = {
    API_BASE_URL: "YOUR_BACKEND_URL"
};
```

Serve the frontend through a local HTTP server:

```bash
python3 -m http.server 5500
```

Then open:

```text
http://localhost:5500/home.html
```

## 🧪 Testing

Run the backend test suite:

```bash
./mvnw test
```

On Windows:

```bash
mvnw.cmd test
```

## 🚀 Deployment

The application can be deployed as separate frontend, backend, and database services.

```text
                Harborview
                    │
          ┌─────────┴─────────┐
          │                   │
      Frontend             Backend
       Vercel              Railway
                              │
                            MySQL
                           Railway
```

For deployment, configure database credentials, JWT secrets, and other environment-specific values through the hosting platform's environment variables.

Update the frontend API configuration with the deployed Spring Boot backend URL.

## 🎥 Demo

**[Watch the Harborview Project Demo](YOUR_YOUTUBE_URL)**

## 🔗 Related Repository

- **Backend:** [Hotel-Management-System-SpringBoot](https://github.com/vvaishnavii0609/Hotel-Management-System-SpringBoot)
- **Frontend:** [complete-frontend-hotelmanagement](https://github.com/vvaishnavii0609/complete-frontend-hotelmanagement)

## 👩‍💻 Author

**Vaishnavi Nagwekar**

[GitHub](https://github.com/vvaishnavii0609)
