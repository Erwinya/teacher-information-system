# Teacher Information System

RESTful API for managing teachers, students, school classes, and managers — Spring Boot + PostgreSQL.

## Quick start (no Docker)

**PowerShell:**

```powershell
.\mvnw.cmd spring-boot:run "-Dspring-boot.run.profiles=local"
```

> PowerShell'de `-D...` mutlaka tırnak içinde olmalı.

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
