
# Spring Security JWT Authentication

This project demonstrates how to implement JWT-based authentication in a Spring Boot application using Spring Security.

## 🔐 Features

- Spring Boot 3.x
- Spring Security
- JWT Token Authentication
- Role-based Access
- Login/Signup API
- In-memory or DB user storage

## 🚀 How to Run

1. Clone the repository
2. Open in your IDE (Eclipse/IntelliJ)
3. Configure `application.properties` (if needed)
4. Run `SpringBootSecurityJwtProjectApplication.java`
5. Test APIs using Postman

## 🛡️ Endpoints

| Method | Endpoint         | Description               |
|--------|------------------|---------------------------|
| POST   | /api/auth/login  | Authenticate user         |
| POST   | /api/auth/signup | Register new user         |
| GET    | /api/user/me     | Get current logged-in user|

## 📦 Dependencies

- Spring Boot Starter Web
- Spring Boot Starter Security
- jwt (JSON Web Tokens)
- Spring Data JPA

