# Task Scheduler Microservice

A monorepo for the Task Scheduler project containing a Task Microservice built with Java Helidon. The service is configured for PostgreSQL with AWS RDS, providing full CRUD operations and task management functionalities.

## Features
- **CRUD Operations**: Create, Read, Update, and Delete tasks.
- **Database Integration**: Configured with AWS RDS PostgreSQL. Can be adapted to other PostgreSQL databases.
- **Java Helidon**: Utilizes Java Helidon for building the microservice.
- **Environment Configuration**: Uses IntelliJ environment variables for sensitive data configuration.

## Prerequisites
- Java 21+
- Maven
- PostgreSQL database (AWS RDS recommended but any PostgreSQL-compatible database can be used)

## Building and Running
1. **Clone the repository**:

    ```sh
    git clone https://github.com/21satvik/task-scheduler-microservice.git
    ```

2. **Navigate to the project directory**:

    ```sh
    cd task-scheduler-microservice
    cd tasks-microservice
    ```
   
3. **Build the Project**:
    ```bash
    mvn clean
    mvn package
    ```

2. **Run the Application**:
    ```bash
    java -jar target/your-artifact.jar
    ```

## Configuration
Sensitive data such as database credentials should be managed using environment variables.

## Current Status
This project is currently under development. Future updates will include additional microservices, such as a User Microservice.

## Contributing
Contributions are welcome! Please fork the repository and submit a pull request.

---

Feel free to adjust or let me know if there’s anything else you’d like to add!