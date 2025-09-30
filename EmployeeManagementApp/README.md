# Employee Management App

A comprehensive RESTful web application built with Spring Boot for managing employee data with secure authentication and API documentation.

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [API Endpoints](#api-endpoints)
- [Database](#database)
- [Docker Support](#docker-support)
- [Security](#security)
- [Testing](#testing)
- [Project Structure](#project-structure)
- [Contributing](#contributing)

## Features

- **Employee CRUD Operations**: Create, read, update, and delete employee records
- **Role-based Filtering**: Filter employees by their roles
- **RESTful API**: Well-structured REST endpoints
- **Data Validation**: Input validation using Bean Validation
- **Security**: Spring Security integration with basic authentication
- **API Documentation**: Interactive Swagger/OpenAPI documentation
- **Database Integration**: H2 in-memory database for development
- **Containerization**: Docker support for easy deployment
- **Exception Handling**: Global exception handling for better error responses

## Tech Stack

- **Java 17**: Programming language
- **Spring Boot 3.5.5**: Application framework
- **Spring Web**: REST API development
- **Spring Data JPA**: Data persistence layer
- **Spring Security**: Authentication and authorization
- **H2 Database**: In-memory database for development
- **Lombok**: Reduces boilerplate code
- **Maven**: Build and dependency management
- **Swagger/OpenAPI 3**: API documentation
- **Docker**: Containerization

## Prerequisites

- Java 17 or higher
- Maven 3.6+ (or use the included Maven wrapper)
- Docker (optional, for containerization)

## Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/rojabugade/Java-Reboot.git
   cd Java-Reboot/EmployeeManagementApp
   ```

2. **Build the project**
   ```bash
   ./mvnw clean compile
   ```

## Running the Application

### Method 1: Using Maven
```bash
./mvnw spring-boot:run
```

### Method 2: Using Java JAR
```bash
./mvnw clean package
java -jar target/EmployeeManagementApp-0.0.1-SNAPSHOT.jar
```

### Method 3: Using Docker
```bash
# Build Docker image
docker build -t employee-management-app .

# Run the container
docker run -p 8080:8080 employee-management-app
```

The application will start on `http://localhost:8080`

## API Documentation

Once the application is running, you can access the interactive API documentation at:

- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/v3/api-docs`

## API Endpoints

### Employee Management

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `POST` | `/api/employees` | Create a new employee | Yes |
| `GET` | `/api/employees` | Get all employees | Yes |
| `GET` | `/api/employees/{id}` | Get employee by ID | Yes |
| `GET` | `/api/employees/role/{role}` | Get employees by role | Yes |

### Health Check

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `GET` | `/health` | Application health status | No |

### Example Request Body (Create Employee)

```json
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "role": "DEVELOPER",
  "salary": 75000.00
}
```

## Database

The application uses **H2 in-memory database** for development purposes.

### Database Access
- **H2 Console**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:employeedb`
- **Username**: `sa`
- **Password**: (empty)

### Database Schema
The application automatically creates the following table:
- `employees`: Stores employee information (id, name, email, role, salary)

## Docker Support

The application includes a multi-stage Dockerfile for optimized containerization:

```dockerfile
# Build the application
docker build -t employee-management-app .

# Run the container
docker run -p 8080:8080 employee-management-app
```

## Security

The application uses Spring Security with basic authentication:

- **Default Username**: `admin`
- **Default Password**: `admin123`

All API endpoints (except health check) require authentication.

## Testing

Run the test suite:

```bash
# Run all tests
./mvnw test

# Run tests with coverage
./mvnw test jacoco:report
```

The project includes:
- Unit tests for services
- Integration tests for controllers
- API documentation tests

## Project Structure

```
src/
├── main/
│   ├── java/com/
│   │   ├── config/           # Configuration classes
│   │   │   ├── OpenApiConfig.java
│   │   │   └── SecurityConfig.java
│   │   ├── controller/       # REST controllers
│   │   │   └── EmployeeController.java
│   │   ├── dto/             # Data Transfer Objects
│   │   │   └── EmployeeDTO.java
│   │   ├── entity/          # JPA entities
│   │   │   └── Employee.java
│   │   ├── exception/       # Exception handling
│   │   │   ├── GlobalExceptionHandler.java
│   │   │   └── ResourceNotFoundException.java
│   │   ├── repository/      # Data repositories
│   │   │   └── EmployeeRepository.java
│   │   ├── service/         # Business logic
│   │   │   └── EmployeeService.java
│   │   └── employeeManagementApp/
│   │       └── EmployeeManagementAppApplication.java
│   └── resources/
│       └── application.properties
└── test/                    # Test classes
```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Authors

- **Roja Bugade** - *Initial work* - [rojabugade](https://github.com/rojabugade)

## Support

If you encounter any issues or have questions, please [create an issue](https://github.com/rojabugade/Java-Reboot/issues) on GitHub.

---


## Built using

![Java](https://img.shields.io/badge/JAVA-21%20LTS-orange?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/SPRING%20BOOT-3.5.5-brightgreen?style=for-the-badge&logo=spring&logoColor=white)
![Docker](https://img.shields.io/badge/DOCKER-SUPPORTED-blue?style=for-the-badge&logo=docker&logoColor=white)
![Tests](https://img.shields.io/badge/TESTS-100%25%20PASSING-brightgreen?style=for-the-badge&logo=github&logoColor=white)
![Maven](https://img.shields.io/badge/MAVEN-BUILD-red?style=for-the-badge&logo=apache-maven&logoColor=white)
![H2](https://img.shields.io/badge/H2-DATABASE-lightblue?style=for-the-badge&logo=h2&logoColor=white)
![Security](https://img.shields.io/badge/SPRING-SECURITY-green?style=for-the-badge&logo=spring&logoColor=white)

- **Java 17** - Programming language
- **Spring Boot 3.5.5** - Application framework
- **Spring Security** - Authentication & authorization
- **Spring Data JPA** - Data persistence
- **H2 Database** - In-memory database
- **Maven** - Build & dependency management
- **Swagger/OpenAPI** - API documentation
- **Docker** - Containerization