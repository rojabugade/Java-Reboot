# Todo Application

A comprehensive Spring Boot REST API application for managing todo items with full CRUD operations, pagination, search functionality, and built-in Swagger documentation.

## Features

- **Complete CRUD Operations**: Create, Read, Update, and Delete todo items
- **Pagination Support**: Retrieve todos with customizable page size and page number
- **Search Functionality**: Search todos by keyword in titles
- **Data Validation**: Input validation using Jakarta Bean Validation
- **Exception Handling**: Global exception handling with custom error responses
- **API Documentation**: Interactive Swagger UI for testing and documentation
- **In-Memory Database**: H2 database for development and testing
- **REST API**: RESTful endpoints following best practices

## Technology Stack

- **Java 17**: Modern Java version with enhanced features
- **Spring Boot 3.5.5**: Latest Spring Boot framework
- **Spring Data JPA**: For database operations and ORM
- **Spring Web**: For REST API development
- **H2 Database**: In-memory database for development
- **Jakarta Validation**: For request validation
- **SpringDoc OpenAPI 3**: For API documentation (Swagger)
- **Maven**: Dependency management and build tool

## Prerequisites

Before running the application, ensure you have:

- Java 17 or higher installed
- Maven 3.6+ installed
- Git (for cloning the repository)

## Installation & Setup

### 1. Clone the Repository
```bash
git clone <repository-url>
cd todoapp
```

### 2. Build the Application
```bash
mvn clean compile
```

### 3. Run the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Documentation

### Swagger UI
Once the application is running, you can access the interactive API documentation at:
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/api-docs

### H2 Database Console
Access the H2 database console for data inspection:
- **URL**: http://localhost:8080/h2-console
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: *(leave empty)*

## API Endpoints

### Base URL: `http://localhost:8080/api/todos`

| Method | Endpoint | Description | Parameters |
|--------|----------|-------------|------------|
| GET | `/` | Get all todos | None |
| GET | `/{id}` | Get todo by ID | `id` (path parameter) |
| POST | `/` | Create a new todo | Todo object in request body |
| PUT | `/{id}` | Update existing todo | `id` (path parameter), Todo object in request body |
| DELETE | `/{id}` | Delete todo by ID | `id` (path parameter) |
| GET | `/page` | Get todos with pagination | `page` (default: 0), `size` (default: 5) |
| GET | `/search` | Search todos by keyword | `keyword` (query parameter) |

### Request/Response Examples

#### Create a Todo
```bash
POST /api/todos
Content-Type: application/json

{
  "title": "Learn Spring Boot",
  "completed": false
}
```

#### Response
```json
{
  "id": 1,
  "title": "Learn Spring Boot",
  "completed": false
}
```

#### Get Todo by ID
```bash
GET /api/todos/1
```

#### Response
```json
{
  "status": "success",
  "message": "Todo fetched",
  "data": {
    "id": 1,
    "title": "Learn Spring Boot",
    "completed": false
  }
}
```

#### Search Todos
```bash
GET /api/todos/search?keyword=Spring
```

#### Get Paginated Todos
```bash
GET /api/todos/page?page=0&size=10
```

## Project Structure

```
src/
├── main/
│   ├── java/com/example/todoapp/
│   │   ├── TodoappApplication.java          # Main application class
│   │   ├── controller/
│   │   │   ├── TodoController.java          # REST controller
│   │   │   ├── GlobalExceptionHandler.java  # Global exception handling
│   │   │   └── TodoNotFoundException.java   # Custom exception
│   │   ├── model/
│   │   │   ├── Todo.java                    # Todo entity
│   │   │   ├── TodoDTO.java                 # Data Transfer Object
│   │   │   ├── ApiResponse.java             # API response wrapper
│   │   │   └── User.java                    # User entity
│   │   ├── repository/
│   │   │   └── TodoRepository.java          # Data access layer
│   │   └── service/
│   │       └── TodoService.java             # Business logic layer
│   └── resources/
│       └── application.properties           # Application configuration
└── test/
    └── java/com/example/todoapp/
        └── TodoappApplicationTests.java     # Test classes
```

## Configuration

The application uses the following default configurations in `application.properties`:

```properties
# Server Configuration
server.port=8080
spring.application.name=todoapp

# H2 Database Configuration
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=update

# Swagger/OpenAPI Configuration
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.operationsSorter=method
springdoc.swagger-ui.tagsSorter=alpha
```

## Testing

### Run Unit Tests
```bash
mvn test
```

### Manual Testing with cURL

#### Create a Todo
```bash
curl -X POST http://localhost:8080/api/todos \
  -H "Content-Type: application/json" \
  -d '{"title": "Test Todo", "completed": false}'
```

#### Get All Todos
```bash
curl -X GET http://localhost:8080/api/todos
```

#### Update a Todo
```bash
curl -X PUT http://localhost:8080/api/todos/1 \
  -H "Content-Type: application/json" \
  -d '{"title": "Updated Todo", "completed": true}'
```

#### Delete a Todo
```bash
curl -X DELETE http://localhost:8080/api/todos/1
```

## Development & Deployment

### Development Mode
The application includes Spring Boot DevTools for hot reloading during development:
```bash
mvn spring-boot:run
```

### Building for Production
```bash
mvn clean package
java -jar target/todoapp-0.0.1-SNAPSHOT.jar
```

### Docker Deployment (Optional)
Create a `Dockerfile`:
```dockerfile
FROM openjdk:17-jdk-slim
COPY target/todoapp-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

Build and run:
```bash
docker build -t todoapp .
docker run -p 8080:8080 todoapp
```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Troubleshooting

### Common Issues

1. **Port 8080 already in use**
   - Change the port in `application.properties`: `server.port=8081`

2. **Java version compatibility**
   - Ensure you're using Java 17 or higher
   - Check with: `java -version`

3. **Maven build failures**
   - Clean and rebuild: `mvn clean compile`
   - Check Maven version: `mvn -version`

### Support

For support and questions:
- Check the [Swagger UI](http://localhost:8080/swagger-ui.html) for API documentation
- Review the H2 console for database inspection
- Check application logs for error details

## Future Enhancements

- [ ] User authentication and authorization
- [ ] Todo categories and tags
- [ ] Due dates and reminders
- [ ] File attachments
- [ ] Email notifications
- [ ] External database integration (PostgreSQL/MySQL)
- [ ] Caching with Redis
- [ ] API rate limiting
- [ ] Docker Compose setup
- [ ] CI/CD pipeline integration

---

**Happy Coding!**