# Veterinary Management System

A comprehensive Spring Boot application for managing veterinary clinic operations including customers, animals, doctors, vaccines, and appointments.

## 🏥 Features

### Core Functionality
- **Customer Management**: Register and manage pet owners with contact information
- **Animal Management**: Track pets with detailed information (species, breed, medical history)
- **Doctor Management**: Manage veterinary doctors and their working schedules
- **Vaccine Management**: Track vaccine applications and protection periods
- **Appointment Scheduling**: Intelligent appointment booking with conflict detection

### Business Logic Constraints
- **No Overlapping Appointments**: Prevents double-booking for doctors
- **Doctor Availability**: Ensures appointments are only scheduled during doctor's working hours
- **Vaccine Protection Periods**: Prevents appointments during active vaccine protection periods
- **Data Validation**: Comprehensive input validation for all entities

## 🏗️ Architecture

### Layered Architecture
```
┌─────────────────┐
│   Controllers   │ ← REST API endpoints
├─────────────────┤
│    Services     │ ← Business logic layer
├─────────────────┤
│  Repositories   │ ← Data access layer
├─────────────────┤
│    Entities     │ ← Database models
└─────────────────┘
```

### Technology Stack
- **Framework**: Spring Boot 3.x
- **Database**: PostgreSQL
- **ORM**: Spring Data JPA
- **Validation**: Bean Validation (Jakarta)
- **Build Tool**: Maven
- **Java Version**: 17+

## 📋 Prerequisites

Before running this application, make sure you have:

- **Java 17** or higher
- **Maven 3.6** or higher
- **PostgreSQL 12** or higher
- **Git** (for cloning the repository)

## 🚀 Installation & Setup

### 1. Clone the Repository
```bash
git clone <repository-url>
cd Veteriner_Yonetim_Sistemi
```

### 2. Database Setup

#### Option A: Using pgAdmin
1. Open pgAdmin
2. Create a new database named `veteriner_db`
3. Set the character encoding to `UTF-8`

#### Option B: Using psql Command Line
```sql
CREATE DATABASE veteriner_db WITH ENCODING 'UTF8';
```

### 3. Configure Application Properties

Edit `src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/veteriner_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Server Configuration
server.port=8080
```

### 4. Build and Run

```bash
# Clean and compile
mvn clean compile

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### 5. Load Sample Data (Optional)

After the application starts, you can load sample data using the provided SQL script:

```bash
psql -U your_username -d veteriner_db -f veteriner_db.sql
```

## 📚 API Documentation

### Base URL
```
http://localhost:8080
```

### Available Endpoints

#### Customer Management
- `GET /api/customers` - Get all customers
- `GET /api/customers/{id}` - Get customer by ID
- `POST /api/customers` - Create new customer
- `PUT /api/customers/{id}` - Update customer
- `DELETE /api/customers/{id}` - Delete customer

#### Animal Management
- `GET /api/animals` - Get all animals
- `GET /api/animals/{id}` - Get animal by ID
- `POST /api/animals` - Create new animal
- `PUT /api/animals/{id}` - Update animal
- `DELETE /api/animals/{id}` - Delete animal

#### Doctor Management
- `GET /api/doctors` - Get all doctors
- `GET /api/doctors/{id}` - Get doctor by ID
- `POST /api/doctors` - Create new doctor
- `PUT /api/doctors/{id}` - Update doctor
- `DELETE /api/doctors/{id}` - Delete doctor

#### Available Date Management
- `GET /api/available-dates` - Get all available dates
- `GET /api/available-dates/{id}` - Get available date by ID
- `POST /api/available-dates` - Create new available date
- `PUT /api/available-dates/{id}` - Update available date
- `DELETE /api/available-dates/{id}` - Delete available date

#### Vaccine Management
- `GET /api/vaccines` - Get all vaccines
- `GET /api/vaccines/{id}` - Get vaccine by ID
- `POST /api/vaccines` - Create new vaccine
- `PUT /api/vaccines/{id}` - Update vaccine
- `DELETE /api/vaccines/{id}` - Delete vaccine

#### Appointment Management
- `GET /api/appointments` - Get all appointments
- `GET /api/appointments/{id}` - Get appointment by ID
- `POST /api/appointments` - Create new appointment
- `PUT /api/appointments/{id}` - Update appointment
- `DELETE /api/appointments/{id}` - Delete appointment

## 🧪 Testing

### Postman Collection

A comprehensive Postman collection is included in the project:
- File: `Veteriner_Yonetim_Sistemi.postman_collection.json`
- Contains all API endpoints with example requests
- Pre-configured with sample data

### Import Postman Collection

1. Open Postman
2. Click "Import" button
3. Select the `Veteriner_Yonetim_Sistemi.postman_collection.json` file
4. The collection will be imported with all endpoints ready to test


## 🔧 Configuration

### Environment Variables

You can override default settings using environment variables:

```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/veteriner_db
export SPRING_DATASOURCE_USERNAME=your_username
export SPRING_DATASOURCE_PASSWORD=your_password
export SERVER_PORT=8080
```

### Logging Configuration

Add to `application.properties` for detailed logging:

```properties
# Logging Configuration
logging.level.com.veteriner.yonetim.sistemi=DEBUG
logging.level.org.springframework.web=DEBUG
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

## 🚨 Error Handling

The application includes comprehensive error handling:

- **400 Bad Request**: Validation errors, business logic violations
- **404 Not Found**: Resource not found
- **409 Conflict**: Resource already exists, appointment conflicts
- **500 Internal Server Error**: Unexpected server errors


## 📊 Database Schema

### Entity Relationships

```
Customer (1) ←→ (N) Animal
Animal (1) ←→ (N) Vaccine
Animal (1) ←→ (N) Appointment
Doctor (1) ←→ (N) AvailableDate
Doctor (1) ←→ (N) Appointment
```

### Key Tables

- **customers**: Customer information
- **animals**: Pet information linked to customers
- **doctors**: Veterinary doctor information
- **available_dates**: Doctor working schedules
- **vaccines**: Vaccine records for animals
- **appointments**: Scheduled appointments

## 🔒 Security Considerations

- Input validation on all endpoints
- SQL injection prevention through JPA
- Proper error handling without exposing sensitive information
- Database connection security

## 🚀 Deployment

### Docker Deployment

```dockerfile
FROM openjdk:17-jdk-slim
COPY target/veteriner-yonetim-sistemi-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

### Production Configuration

For production deployment, update `application.properties`:

```properties
# Production Database
spring.datasource.url=jdbc:postgresql://production-db:5432/veteriner_db
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

# JPA Configuration for Production
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false

# Security
server.ssl.enabled=true
server.ssl.key-store=classpath:keystore.p12
server.ssl.key-store-password=${SSL_PASSWORD}
```



