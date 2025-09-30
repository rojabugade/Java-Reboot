# Student Management API 🎓

A comprehensive RESTful API for managing student records built with **Spring Boot 3.5.6** and **Java 21 LTS**.

## 🚀 Features

- **Full CRUD Operations** for student management
- **Swagger/OpenAPI 3** documentation
- **Input Validation** with custom error handling
- **Docker Support** with multi-stage builds
- **Health Checks** with Spring Boot Actuator
- **Java 21 LTS** with modern JVM optimizations
- **Comprehensive Testing** (Unit & Integration tests)

## 📋 API Endpoints

| Method | Endpoint | Description | Request Body | Response |
|--------|----------|-------------|--------------|----------|
| `POST` | `/api/students` | Create a new student | Student JSON | Created Student |
| `GET` | `/api/students` | Get all students | None | List of Students |
| `GET` | `/api/students/{id}` | Get student by ID | None | Student |
| `PUT` | `/api/students/{id}` | Update student | Student JSON | Updated Student |
| `DELETE` | `/api/students/{id}` | Delete student | None | Success message |

### Student Model
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com",
  "course": "Computer Science"
}
```

## 🛠️ Prerequisites

- **Java 21 LTS** (Eclipse Adoptium recommended)
- **Maven 3.9+**
- **Docker** (for containerization)

## 🏃‍♂️ Quick Start

### Local Development

1. **Clone the repository**
   ```bash
   git clone https://github.com/rojabugade/Java-Reboot.git
   cd Java-Reboot/student-api
   ```

2. **Build the project**
   ```bash
   # Using the convenience script (Windows)
   .\mvnw21.cmd clean install
   
   # Or with environment variables
   $env:JAVA_HOME="C:\Program Files\Eclipse Adoptium\jdk-21.0.3.9-hotspot"
   $env:PATH="$env:JAVA_HOME\bin;$env:PATH"
   .\mvnw clean install
   ```

3. **Run the application**
   ```bash
   .\mvnw21.cmd spring-boot:run
   ```

4. **Access the API**
   - **Application**: http://localhost:8080
   - **Swagger UI**: http://localhost:8080/swagger-ui.html
   - **API Docs**: http://localhost:8080/api-docs
   - **Health Check**: http://localhost:8080/actuator/health

### Docker Deployment

1. **Build and run with Docker Compose**
   ```bash
   docker-compose up --build
   ```

2. **Or build and run manually**
   ```bash
   docker build -t student-api .
   docker run -p 8080:8080 student-api
   ```

## 🧪 Testing

### Run All Tests
```bash
.\mvnw21.cmd test
```

### Test Categories

- **Unit Tests**: `StudentControllerTest.java` - Tests individual components
- **Integration Tests**: `StudentApiIntegrationTest.java` - Tests complete workflows

### Manual Testing with curl

**Create a student:**
```bash
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Alice Johnson",
    "email": "alice.johnson@example.com",
    "course": "Data Science"
  }'
```

**Get all students:**
```bash
curl http://localhost:8080/api/students
```

**Get student by ID:**
```bash
curl http://localhost:8080/api/students/1
```

**Update student:**
```bash
curl -X PUT http://localhost:8080/api/students/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Alice Smith",
    "email": "alice.smith@example.com",
    "course": "Machine Learning"
  }'
```

**Delete student:**
```bash
curl -X DELETE http://localhost:8080/api/students/1
```

## 📚 Documentation

### Swagger/OpenAPI
- **Interactive API Documentation**: http://localhost:8080/swagger-ui.html
- **OpenAPI Specification**: http://localhost:8080/api-docs

### Monitoring
- **Health Check**: http://localhost:8080/actuator/health
- **Application Info**: http://localhost:8080/actuator/info
- **Metrics**: http://localhost:8080/actuator/metrics

## 🐳 Docker Configuration

### Dockerfile Features
- **Multi-stage build** for optimized image size
- **Non-root user** for security
- **Health checks** built-in
- **JVM optimization** for containerized environments

### Environment Variables
- `SPRING_PROFILES_ACTIVE`: Set to `docker` for container deployment
- `JAVA_OPTS`: JVM options (default: `-XX:+UseZGC -XX:+EnableDynamicAgentLoading -Xmx512m -Xms256m`)

## 🏗️ Project Structure

```
student-api/
├── src/
│   ├── main/
│   │   ├── java/com/example/student_api/
│   │   │   ├── config/           # Configuration classes
│   │   │   ├── controller/       # REST controllers
│   │   │   ├── model/           # Entity models
│   │   │   ├── service/         # Business logic
│   │   │   └── StudentService.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── application-docker.properties
│   └── test/
│       └── java/com/example/student_api/
│           ├── controller/       # Unit tests
│           └── integration/      # Integration tests
├── Dockerfile
├── docker-compose.yml
├── mvnw21.cmd                   # Java 21 convenience script
└── pom.xml
```

## 🔧 Configuration

### Application Properties
- **Server Port**: 8080
- **Swagger Path**: `/swagger-ui.html`
- **API Docs Path**: `/api-docs`
- **Health Check**: `/actuator/health`

### Validation Rules
- **Name**: Required, not blank
- **Email**: Required, valid email format
- **Course**: Required, not blank

## 🚀 Performance Optimizations

- **Java 21 ZGC**: Low-latency garbage collection
- **JVM Options**: Optimized for container environments
- **Maven Wrapper**: Consistent build experience
- **Docker Multi-stage**: Reduced image size

## 🔍 Health & Monitoring

The application includes comprehensive health checks:
- **Application Health**: Basic Spring Boot health indicators
- **Custom Metrics**: Available via `/actuator/metrics`
- **Docker Health Check**: Built into container

## 📈 Future Enhancements

- [ ] Database integration (PostgreSQL/MySQL)
- [ ] Authentication & Authorization (Spring Security)
- [ ] Caching (Redis)
- [ ] Rate limiting
- [ ] Metrics & Monitoring (Prometheus/Grafana)
- [ ] CI/CD Pipeline (GitHub Actions)

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License.

## 📞 Support

For support, please contact: [rojabugade](https://github.com/rojabugade)

---
**Built with ❤️ using Spring Boot 3.5.6 and Java 21 LTS**