package com.example.student_api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Student Management API",
        version = "1.0.0",
        description = "A comprehensive API for managing student records with full CRUD operations",
        contact = @Contact(
            name = "API Support",
            email = "support@studentapi.com",
            url = "https://github.com/rojabugade/Java-Reboot"
        ),
        license = @License(
            name = "MIT License",
            url = "https://opensource.org/licenses/MIT"
        )
    ),
    servers = {
        @Server(
            description = "Local Development Server",
            url = "http://localhost:8080"
        ),
        @Server(
            description = "Docker Container",
            url = "http://localhost:8080"
        )
    }
)
public class OpenApiConfig {
}