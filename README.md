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
Проект использует PostgreSQL. Для запуска базы данных используется Docker Compose.

1. Запустите контейнер с БД:
   ```bash
   docker compose up -d
Параметры подключения по умолчанию:

Хост: localhost

Порт: 5433 (проброшен из контейнера)

База данных: taskdb

Пользователь: taskuser

Пароль: taskpass

Запуск приложения
bash
./mvnw spring-boot:run
Приложение запустится на http://localhost:8080.

Документация API (Swagger)
После запуска приложения интерактивная документация доступна по адресу:
👉 http://localhost:8080/swagger-ui/index.html

Эндпоинты API
Метод	URL	                    Назначение	       Успешный ответ
POST	/api/tasks	            Создать задачу	        201 Created
GET	    /api/tasks	            Получить список задач   200 OK
GET	    /api/tasks/{id}	        Получить задачу по ID	200 OK
PATCH	/api/tasks/{id}/status	Изменить статус	        200 OK
DELETE	/api/tasks/{id}	        Удалить задачу	        204 No Content
Примеры запросов к API
1. Создать задачу
   http
   POST /api/tasks
   Content-Type: application/json

{
"title": "Подготовить README",
"description": "Добавить инструкцию по запуску проекта"
}
Ответ (201 Created):

json
{
"id": 1,
"title": "Подготовить README",
"description": "Добавить инструкцию по запуску проекта",
"status": "NEW",
"createdAt": "2026-10-19T00:28:34.093502"
}
2. Получить список всех задач
   http
   GET /api/tasks
3. Получить задачу по ID
   http
   GET /api/tasks/1
4. Изменить статус задачи
   http
   PATCH /api/tasks/1/status
   Content-Type: application/json

{
"status": "IN_PROGRESS"
}
5. Удалить задачу
   http
   DELETE /api/tasks/1
   Пример файла конфигурации (application.properties)
   Реальные пароли не хранятся в репозитории. В продакшене используются переменные окружения.

properties
spring.application.name=task-manager

# Настройки подключения к БД
spring.datasource.url=jdbc:postgresql://localhost:5433/taskdb
spring.datasource.username=taskuser
spring.datasource.password=taskpass
spring.datasource.driver-class-name=org.postgresql.Driver

# Настройки JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Отключаем Liquibase (пока не используются миграции)
spring.liquibase.enabled=false
Обработка ошибок
Сервис корректно обрабатывает ошибки с помощью @RestControllerAdvice:

400 Bad Request — при ошибках валидации (пустой title, слишком длинное описание, неверный статус).

404 Not Found — если задача с указанным ID не найдена.

