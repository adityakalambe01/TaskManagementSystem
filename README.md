# Task Management System

This is a Java Spring Boot application for managing tasks, built with Maven. The application is containerized using Docker, and you can easily run it using Docker Compose.

## Prerequisites

Before you begin, ensure you have the following software installed on your machine:

- **Java 8** or higher
- **Maven** 3.6 or higher
- **Docker Compose** 3.0 or higher

## Installation

1. **Clone the Repository**

   ```bash
   git clone https://github.com/adityakalambe01/TaskManagementSystem.git
   cd TaskManagementSystem
   ```

2. **Build the Maven Project**

   Ensure that the application is built and all dependencies are resolved:

   ```bash
   mvn clean install
   ```

## Running the Application

You can run the application using Docker Compose with a single command.

1. **Run Docker Compose**

   Make sure you are in the root directory of the project, where the `docker-compose.yml` file is located.

   ```bash
   docker-compose up
   ```

   This command will:

    - Build the Docker image for the Spring Boot application.
    - Start the application and any other services defined in `docker-compose.yml`.

2. **Access the Application**

   Once the containers are up and running, you can access the application in your web browser at:

   ```bash
   http://localhost:9095
   ```

## API Documentation

The API documentation for this project is available on Postman. You can explore and test the API endpoints by visiting the following link:

[Task Management System API Documentation](https://www.postman.com/adityakalambe20/workspace/taskmanagementsystemapi)

## Stopping the Application

To stop the application and remove the containers, run:

```bash
docker-compose down
```

## Troubleshooting

If you encounter any issues, consider the following steps:

- Check the logs for any errors using:

  ```bash
  docker-compose logs
  ```

- Ensure that no other application is using port `9095` (or the port you've configured in the application).

