# Teacher Information System

RESTful API for managing teachers, students, school classes, and managers — Spring Boot + PostgreSQL.

**Repository:** [Erwinya/teacher-information-system](https://github.com/Erwinya/teacher-information-system)

## Quick start (no Docker)

**PowerShell:**

```powershell
.\mvnw.cmd spring-boot:run "-Dspring-boot.run.profiles=local"
```

> In PowerShell, keep `-D...` inside quotes.

API: http://localhost:8081  
Swagger: http://localhost:8081/swagger-ui.html

## Docker

```bash
docker compose up --build
```

## Tests

```powershell
.\mvnw.cmd test
```

## License

MIT
