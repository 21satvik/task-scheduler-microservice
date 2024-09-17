# Task Scheduler

A monorepo for the Task Scheduler project that includes multiple microservices for managing tasks and users. The project is built using Java Helidon and configured for PostgreSQL with AWS RDS.

## Microservices
- **Task Scheduler Microservice**: Handles task management including CRUD operations.

## Features
- **Modular Architecture**: Each microservice is independently developed and deployed.
- **Database Integration**: Configured with AWS RDS PostgreSQL. Can be adapted to other PostgreSQL databases.
- **Java Helidon**: Utilizes Java Helidon for building microservices.
- **Environment Configuration**: Uses environment variables for sensitive data configuration.

## Prerequisites
- Java 21+
- Maven
- PostgreSQL database (AWS RDS recommended but any PostgreSQL-compatible database can be used)

## Building and Running
Currently, you can test individual microservices by following these steps:

1. **Clone the repository**:

    ```sh
    git clone https://github.com/21satvik/task-scheduler-microservice.git
    ```

2. **Navigate to the microservice directory**:

    ```sh
    cd task-scheduler-microservice
    cd tasks-microservice
    ```

3. **Build the Microservice**:

    ```sh
    mvn clean
    mvn package
    ```

4. **Run the Microservice**:

    ```sh
    java -jar target/your-artifact.jar
    ```

## Configuration
Sensitive data such as database credentials should be managed using environment variables.

## Current Status
The project is currently under development. Future updates will include additional microservices.

## Contributing
Contributions are welcome! Please fork the repository and submit a pull request.