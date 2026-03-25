# Address Book App - Spring Boot

## Overview
A Spring Boot REST application to manage Address Book contacts.
Developed as part of BridgeLabz Full-Stack Training (PP04).

## Tech Stack
- Java 17
- Spring Boot 3.2.3
- Spring Web (REST)
- Maven
- Lombok

## Project Structure
```
src/
 +-- main/
      +-- java/com/addressbook/
      |    +-- controller/   - REST Controllers
      |    +-- service/      - Service Layer
      |    +-- dto/          - Data Transfer Objects
      |    +-- model/        - Domain Models
      +-- resources/
           +-- application.properties
```

## Git Branching Strategy (Gitflow)
| Branch                     | Purpose                                     |
|----------------------------|---------------------------------------------|
| main                       | README only                                 |
| develop                    | Integration branch                          |
| UC1-setup-spring-project   | Section 1 UC1 - Project Setup               |
| UC2-rest-controller-curl   | Section 1 UC2 - REST Controller + CURL      |
| UC3-dto-model-intro        | Section 2 UC1 - DTO and Model               |
| UC4-service-layer          | Section 2 UC2 - Service Layer + @Autowired  |
| UC5-service-store-data     | Section 2 UC3 - In-Memory List Storage      |

## CURL Commands

### GET all contacts
curl -X GET http://localhost:8080/addressbook/contacts

### GET contact by ID
curl -X GET http://localhost:8080/addressbook/contacts/1

### POST - Add new contact
curl -X POST http://localhost:8080/addressbook/contacts -H "Content-Type: application/json" -d "{\"name\":\"John Doe\",\"phone\":\"9876543210\",\"email\":\"john@example.com\",\"address\":\"123 Main St\"}"

### PUT - Update contact by ID
curl -X PUT http://localhost:8080/addressbook/contacts/1 -H "Content-Type: application/json" -d "{\"name\":\"Jane Doe\",\"phone\":\"9876543211\",\"email\":\"jane@example.com\",\"address\":\"456 Park Ave\"}"

### DELETE - Delete contact by ID
curl -X DELETE http://localhost:8080/addressbook/contacts/1

## Author
Manas Pratap Singh | BridgeLabz Full-Stack Trainee