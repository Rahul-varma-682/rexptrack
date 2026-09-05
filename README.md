# RexpTrack

RexpTrack is a Spring Boot REST API for managing personal expenses.

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- JWT
- Maven
- Postman

## Features

- User authentication
- JWT-based authorization
- Expense CRUD operations
- Request validation
- Centralized exception handling
- MySQL persistence
- RESTful APIs

## Architecture

Controller
   ↓
Service
   ↓
Repository
   ↓
MySQL

## Project Structure

src/main/java/...
├── controller
├── service
├── repository
├── entity
├── dto
├── exception
└── security

## Running the Application

1. Clone the repository
2. Configure MySQL
3. Update application properties
4. Run the application using Maven

## API Testing

### Create Expense

POST /api/expenses
Postman was used to test authentication and expense endpoints.
