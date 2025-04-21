# Core Lending Collateral Management

A microservice component of the Firefly platform that manages collateral and guarantees for lending operations.

## Overview

The Core Lending Collateral Management microservice is responsible for managing collateral assets, cases, liens, valuations, and guarantees associated with loan contracts and applications. It provides a comprehensive API for creating, retrieving, updating, and deleting collateral and guarantee records.

## Architecture

This project is structured as a multi-module Maven application:

- **core-lending-collateral-management-interfaces**: Contains DTOs, interfaces, and enums that define the API contract
- **core-lending-collateral-management-models**: Contains database entity models and repositories
- **core-lending-collateral-management-core**: Contains business logic and service implementations
- **core-lending-collateral-management-web**: Contains REST controllers and web configuration

The service follows a layered architecture:
- **Web Layer**: REST controllers that handle HTTP requests and responses
- **Service Layer**: Business logic implementation
- **Repository Layer**: Data access and persistence

## Features

### Collateral Management
- Collateral Case Management
- Collateral Asset Management
- Collateral Lien Management
- Collateral Party Management
- Collateral Valuation Management

### Guarantee Management
- Guarantee Record Management
- Guarantee Party Management
- Guarantee Valuation Management

## API Documentation

The service provides a RESTful API with the following main endpoints:

### Collateral Endpoints
- `/api/v1/collateral-cases` - Manage collateral cases
- `/api/v1/collateral-assets` - Manage collateral assets
- `/api/v1/collateral-liens` - Manage collateral liens
- `/api/v1/collateral-parties` - Manage collateral parties
- `/api/v1/collateral-valuations` - Manage collateral valuations

### Guarantee Endpoints
- `/api/v1/guarantee-records` - Manage guarantee records
- `/api/v1/guarantee-parties` - Manage guarantee parties
- `/api/v1/guarantee-valuations` - Manage guarantee valuations

Each endpoint supports standard CRUD operations (GET, POST, PUT, DELETE).

API documentation is available via Swagger UI when the service is running at `/swagger-ui.html`.

## Technology Stack

- **Java 21**: Programming language
- **Spring Boot**: Application framework
- **Spring WebFlux**: Reactive web framework
- **Maven**: Build tool
- **Docker**: Containerization
- **Azure Container Registry**: Container registry for deployment
- **GitHub Actions**: CI/CD pipeline

## Getting Started

### Prerequisites

- Java 21 or later
- Maven 3.8 or later
- Docker (for containerization)

### Building the Application

```bash
mvn clean install
```

### Running the Application Locally

```bash
java -jar core-lending-collateral-management-web/target/core-lending-collateral-management.jar
```

Or using Maven:

```bash
mvn spring-boot:run -pl core-lending-collateral-management-web
```

### Building and Running with Docker

```bash
# Build the JAR
mvn clean package

# Build the Docker image
docker build -t core-lending-collateral-management .

# Run the Docker container
docker run -p 8080:8080 core-lending-collateral-management
```

## Development

### Project Structure

```
core-lending-collateral-management/
├── core-lending-collateral-management-interfaces/  # API contracts
├── core-lending-collateral-management-models/      # Database models
├── core-lending-collateral-management-core/        # Business logic
├── core-lending-collateral-management-web/         # Web controllers
├── .github/                                        # GitHub workflows
├── Dockerfile                                      # Docker configuration
└── pom.xml                                         # Maven parent configuration
```

### Adding New Features

1. Define DTOs in the interfaces module
2. Create database entities in the models module
3. Implement business logic in the core module
4. Create REST controllers in the web module

### Testing

Run tests using Maven:

```bash
mvn test
```

## Deployment

The service is deployed as a Docker container to Azure Container Registry. The deployment is automated using GitHub Actions workflows.

### CI/CD Pipeline

The CI/CD pipeline is defined in `.github/workflows/publish.yml` and is triggered on pushes to the `main` and `develop` branches. The pipeline:

1. Builds the application using Maven
2. Runs tests
3. Builds a Docker image
4. Publishes the Docker image to Azure Container Registry

## Contributing

1. Create a feature branch from `develop`
2. Make your changes
3. Write tests for your changes
4. Ensure all tests pass
5. Submit a pull request to `develop`