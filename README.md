Project Workflow
1. User Authentication

Users can register and log in through the React application.

Passwords are securely hashed using BCrypt before being stored in PostgreSQL.

After successful authentication, the server issues a JWT stored in an HTTP-Only Cookie.

Protected endpoints require authentication and enforce role-based authorization (e.g., READY and MODIFY).

2. Project Management

Users with the MODIFY permission can create, update, and delete projects.

Projects can be filtered by various criteria such as project name, category, status, planned dates, and actual dates.

Users with the READY permission have read-only access and can only view project information.

3. Task Management

Each project contains multiple tasks, and tasks can also include nested subtasks.

Users with the MODIFY permission can create, update, and delete tasks and subtasks.

Tasks can be filtered by properties such as name, status, priority, assigned user, and dates.

4. Automatic Project Status Management

The project status is automatically updated based on the status of its tasks.

When the first task changes from New to In Progress, the project automatically starts.
The project's Actual Start Date is set automatically when work begins.
When all tasks are marked as Completed, the project is automatically completed.
The project's Actual End Date is set automatically after the final task is completed.

This ensures that the project lifecycle always reflects the progress of its tasks without requiring manual updates.

5. Advanced Filtering

Projects and tasks support dynamic filtering and searching.

Filtering is implemented using QueryDSL, providing efficient and flexible database queries based on multiple optional criteria.

6. Role-Based Authorization

The application uses permission-based authorization.

READY users can only view projects and tasks.
MODIFY users can create, update, and delete projects, tasks, and subtasks.

Authorization is enforced on protected endpoints to ensure secure access control.

7. Docker Deployment

The application and its required services are containerized using Docker.

The complete application environment can be started using Docker Compose, allowing the backend, PostgreSQL, and other required services to run together in a consistent environment.

To stop the application:
