

https://github.com/user-attachments/assets/b15495bc-29ad-431a-984c-657fa8270f13



<img width="6277" height="971" alt="mermaid-diagram (2)" src="https://github.com/user-attachments/assets/a61cc18b-62e4-4700-8cfa-df5c4a9508a4" />




## Project Workflow

### 1. API Gateway

The application follows a **microservices architecture** with a dedicated **API Gateway** implemented as a separate Spring Boot project.

The API Gateway acts as the single entry point for external client requests and routes incoming requests to the appropriate backend services.

The main Spring Boot application is **not directly exposed to the outside network**. External requests can only reach the backend through the API Gateway.

Communication between the **API Gateway** and the **Spring Boot application** is secured using a shared **secret key**. The API Gateway includes the secret in a custom HTTP header when forwarding requests, and the backend validates this header to ensure that requests originate from the trusted API Gateway.

### 2. User Authentication

Users can register and log in through the React application.

Passwords are securely hashed using **BCrypt** before being stored in **PostgreSQL**.

After successful authentication, the server issues a **JWT** stored in an **HTTP-Only Cookie**.

Protected endpoints require authentication and enforce role-based authorization (**READY** and **MODIFY**).

### 3. Project Management

Users with the **MODIFY** permission can create, update, and delete projects.

Projects can be filtered by **project name**, **status**, **category**, **planned dates**, and **actual dates**.

Users with the **READY** permission have **read-only** access.

### 4. Task Management

Each project contains multiple **tasks**, and every task can include **subtasks**.

Users with the **MODIFY** permission can create, update, and delete tasks and subtasks.

### 5. Automatic Project Status Management

The project status is automatically synchronized with its tasks.

* When the first task changes to **In Progress**, the project status automatically becomes **In Progress**.
* The project's **Actual Start Date** is filled automatically.
* When **all tasks** are marked as **Completed**, the project status automatically becomes **Completed**.
* The project's **Actual End Date** is filled automatically.

### 6. Advanced Filtering

Projects support dynamic filtering using **JPA Criteria API**, allowing efficient and flexible database queries.

### 7. Role-Based Authorization

* **READY** users can only view projects and tasks.
* **MODIFY** users can create, update, and delete projects, tasks, and subtasks.

### 8. Docker Deployment

The application and its required services are containerized using **Docker**.

The complete environment can be started with **Docker Compose**.

To stop the application:

```bash
docker compose down
```

```
