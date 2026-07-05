SMEV API Mock
Mock-сервис на Spring Boot для проверки паспорта по REST API.

Сервис принимает POST запрос на endpoint /api/check/v1/CheckPassport и возвращает тестовый ответ с полями:

isValid
status
decodeDocStatus
result
Технологии
Java 21
Spring Boot 3.5.x
Maven
Docker
Docker Compose
Описание API
Проверка паспорта
URL: POST /api/check/v1/CheckPassport

Headers:

Content-Type: application/json
x-token — необязательный заголовок
Body пример:

{
"familyName": "Иванов",
"firstName": "Иван",
"patronymic": "Иванович",
"series": "1234",
"number": "567890"
}
Пример ответа
{
"isValid": true,
"status": "300",
"decodeDocStatus": "Паспорт действителен",
"issueCode": null,
"issueDate": null,
"invalidityReason": null,
"decodeInvalidityReason": null,
"invaliditySince": null,
"result": {
"success": true,
"errorCode": "",
"description": ""
}
}
Запуск локально
1. Сборка проекта
   mvn clean package
2. Запуск приложения
   java -jar target/smev-api-mock-0.0.1-SNAPSHOT.jar
   Запуск через Docker
   Сборка и запуск
   docker compose up --build
   Доступ к сервису
   После запуска сервис будет доступен по адресу:

http://localhost:8081/api/check/v1/CheckPassport
Пример запроса в Postman
Method
POST

URL
http://localhost:8081/api/check/v1/CheckPassport
Headers
{
"Content-Type": "application/json",
"x-token": "12345"
}
Body
{
"familyName": "Иванов",
"firstName": "Иван",
"patronymic": "Иванович",
"series": "1234",
"number": "567890"
}
Структура проекта
smev-api-mock
├── src
│   └── main
│       ├── java
│       │   └── com.smev_api_mock
│       │       ├── controller
│       │       │   └── CheckController.java
│       │       └── model
│       │           ├── CheckRequest.java
│       │           └── CheckResponse.java
│       └── resources
│           └── application.properties
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
Docker configuration
Dockerfile
Проект собирается в multi-stage режиме:

на первом этапе собирается JAR через Maven;
на втором этапе запускается только JRE-образ с готовым приложением.
docker-compose.yml
Compose-файл используется для удобного запуска приложения в контейнере с пробросом порта 8081.

Возможные проблемы
500 Internal Server Error
Если в ответе приходит 500, проверьте контроллер. Не нужно передавать строку "300" или "301" в HttpStatus.valueOf(...), потому что Spring не воспринимает такие значения как enum-статусы.

Порт не открывается
Проверьте, что:

приложение слушает 8081;
в docker-compose.yml указан проброс 8081:8081;
в Postman используется URL с localhost:8081.
Лицензия
Проект используется как учебный mock-сервис.