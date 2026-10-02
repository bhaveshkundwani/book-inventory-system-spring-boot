# Book Inventory System

A server-rendered web application for managing a book inventory, built with **Spring Boot**, **Thymeleaf**, and **PostgreSQL**. It lets you add, search, delete, and track summary statistics for books in a collection.

## Features

- Add new books with an auto-generated unique Book ID (8-character UUID) if none is provided
- Prevents duplicate Book IDs on creation
- Search for a book by its Book ID
- Delete a book by its Book ID
- Dashboard view with total books, total authors, and total inventory value
- Clean, dedicated pages for each action (add, search, delete, info)

## Tech Stack

| Layer       | Technology                     |
|-------------|--------------------------------|
| Language    | Java 17                        |
| Framework   | Spring Boot 4.1.1 (Web MVC)    |
| Persistence | Spring Data JPA, Hibernate     |
| Database    | PostgreSQL                     |
| View        | Thymeleaf                      |
| Build Tool  | Maven                          |
| Other       | Lombok, Bean Validation        |

## Project Structure

```
src/main/java/com/book/
├── controller/    # MVC controller handling web requests
├── service/       # Business logic (CRUD, ID generation, stats)
├── repository/    # Spring Data JPA repository
└── entity/        # Book JPA entity

src/main/resources/
├── templates/     # Thymeleaf pages (index, addBook, search, deleteBook, info)
├── static/css/    # Custom styles
└── application.yaml
```

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven (or use the included Maven wrapper)
- PostgreSQL

### Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/bhaveshkundwani/book-inventory-system-spring-boot.git
   cd book-inventory-system-spring-boot
   ```

2. **Create the database**
   ```sql
   CREATE DATABASE book_inventory;
   ```

3. **Configure database credentials** in `src/main/resources/application.yaml`:
   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/book_inventory
       username: ${DB_USERNAME}
       password: ${DB_PASSWORD}
   ```
   Set `DB_USERNAME` and `DB_PASSWORD` as environment variables so credentials are never committed.

4. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

5. **Open in your browser**
   ```
   http://localhost:8080
   ```

Tables are created automatically on startup (`ddl-auto: update`).

## Application Routes

| Method | Route        | Description                          |
|--------|--------------|---------------------------------------|
| GET    | `/`          | Home page                             |
| GET    | `/addBook`   | Show add-book form                    |
| POST   | `/addBook`   | Add a new book                        |
| GET    | `/search`    | Show search form                      |
| POST   | `/search`    | Search for a book by Book ID          |
| GET    | `/deleteBook`| Show delete form                      |
| POST   | `/deleteBook`| Delete a book by Book ID              |
| GET    | `/info`      | Inventory summary (totals, value)     |

## Book Data Model

| Field             | Type   | Notes                              |
|-------------------|--------|-------------------------------------|
| `id`              | Long   | Primary key, auto-generated         |
| `bookid`          | String | Unique, auto-generated if omitted   |
| `title`           | String | Required                            |
| `author`          | String | Required                            |
| `publisher`       | String |                                      |
| `publicationYear` | String |                                      |
| `price`           | double |                                      |
| `quantity`        | String |                                      |
| `language`        | String |                                      |

## Author

**Bhavesh Kundwani**
