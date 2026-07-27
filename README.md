<img width="5957" height="902" alt="mermaid-diagram (1)" src="https://github.com/user-attachments/assets/3d53afca-7631-44af-9b97-9f79ba6cd2c1" />




## Project Workflow

### 1. User Authentication

Users can register and log in through the React application.

Passwords are securely hashed using **BCrypt** before being stored in **PostgreSQL**.

After successful authentication, the server issues a **JWT** stored in an **HTTP-Only Cookie**.

Protected endpoints require authentication and enforce role-based authorization (**READY** and **MODIFY**).

### 2. Project Management

Users with the **MODIFY** permission can create, update, and delete projects.

Projects can be filtered by **project name**, **status**, **category**, **planned dates**, and **actual dates**.

Users with the **READY** permission have **read-only** access.

### 3. Task Management

Each project contains multiple **tasks**, and every task can include **subtasks**.

Users with the **MODIFY** permission can create, update, and delete tasks and subtasks.

### 4. Automatic Project Status Management

The project status is automatically synchronized with its tasks.

- When the first task changes to **In Progress**, the project status automatically becomes **In Progress**.
- The project's **Actual Start Date** is filled automatically.
- When **all tasks** are marked as **Completed**, the project status automatically becomes **Completed**.
- The project's **Actual End Date** is filled automatically.

### 5. Advanced Filtering

Projects support dynamic filtering using **Jpa and Criteria**, allowing efficient and flexible database queries.

### 6. Role-Based Authorization

- **READY** users can only view projects and tasks.
- **MODIFY** users can create, update, and delete projects, tasks, and subtasks.

### 7. Docker Deployment

The application and its required services are containerized using **Docker**.

The complete environment can be started with **Docker Compose**.

To stop the application:

```bash
docker compose down
```
