# Task Manager API

REST API для управления задачами, разработанный на Java и Spring Boot.

## Стек технологий
- Java 21
- Spring Boot 3.5.16
- Spring Data JPA
- Spring Web
- Bean Validation (Hibernate Validator)
- PostgreSQL 16 (запускается через Docker)
- SpringDoc OpenAPI (Swagger UI)
- Lombok

## Требования для запуска
- JDK 21+
- Docker и Docker Compose
- Maven 3.8+ (или встроенный `./mvnw`)

## Настройка базы данных
Проект использует PostgreSQL. Запустите базу данных в Docker:
```bash
cd taskmanager-db
docker compose up -d