

---

# Employee Management System

A backend for managing employees using **Spring Boot**, **Spring Security (Basic Auth)**, **MySQL**, and **Swagger**. This project includes robust exception handling with custom and global exceptions. The utility classes are used for consistent API responses, and user roles are defined via constants for access control.

> **Note**: The frontend is built with **Angular** and located in a [separate repository](#).

---

## Features

- **CRUD Operations**: Manage employee records.
- **Role-based Security**: Admin and User roles with Basic Authentication.
- **Custom Exception Handling**: Global and custom exceptions for error management.
- **MySQL Database**: Persistent employee data storage.
- **Swagger**: API documentation and testing UI.

---

## Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/employee-management-system.git
   ```
2. **Configure MySQL**: Update `application.properties` with your database credentials.
3. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```

---

## Endpoints

- **GET /employees**: List all employees (Admin/User)
- **POST /employees**: Add a new employee (Admin)
- **GET /employees/{id}**: Get employee by ID (Admin/User)
- **Swagger UI**: Visit `/swagger-ui.html` for API documentation

---

## Security

- **Admin**: Full access.
- **User**: Read-only access.
- Basic Authentication controls access based on roles.

---

## Author

**Your Name**  
[Frontend Repository](#) | [GitHub](https://github.com/Siddhantthorat/EmployeeManagementSystemProject.git)

---

