# CQRS Bank Account Service

A Spring Boot application implementing **CQRS (Command Query Responsibility Segregation)** and **Event Sourcing** patterns using **Axon Framework**.

##  Architecture

```
┌─────────────────────────────────────────────────────────────────┐
│                        REST API (Port 8066)                      │
├─────────────────────────────┬───────────────────────────────────┤
│      Command Side           │           Query Side              │
│  POST /commands/account/*   │    GET /query/accounts/*          │
├─────────────────────────────┼───────────────────────────────────┤
│   AccountCommandController  │    AccountQueryController         │
│            │                │            │                      │
│   CreateAccountCommand      │    GetAllAccountsQuery            │
│            │                │    GetAccountByIdQuery            │
│   AccountAggregate          │            │                      │
│            │                │    AccountQueryHandler            │
│   AccountCreatedEvent       │            │                      │
│            │                │    AccountRepository (JPA)        │
├────────────┴────────────────┴───────────────────────────────────┤
│                     Axon Server (Port 8124)                      │
│                   Event Store & Message Routing                  │
├─────────────────────────────────────────────────────────────────┤
│                   PostgreSQL / H2 Database                       │
│                      (Query Side Storage)                        │
└─────────────────────────────────────────────────────────────────┘
```

##  Tech Stack

- **Java 17**
- **Spring Boot 4.0.1**
- **Axon Framework 4.10.3**
- **Axon Server** (Event Store)
- **PostgreSQL / H2** (Query Database)
- **Lombok**
- **Swagger/OpenAPI**

## Project Structure

```
src/main/java/org/example/cqrsbankaccountservice/
├── commands/
│   ├── aggregates/
│   │   └── AccountAggregate.java       # Aggregate root
│   ├── commands/
│   │   ├── BaseCommand.java            # Base command class
│   │   └── CreateAccountCommand.java   # Create account command
│   ├── controllers/
│   │   └── AccountCommandController.java
│   └── dtos/
│       └── CreateAccountRequestDTO.java
├── common/
│   ├── enums/
│   │   └── AccountStatus.java          # CREATED, ACTIVATED, SUSPENDED, CLOSED
│   └── events/
│       └── AccountCreatedEvent.java    # Domain event
└── query/
    ├── controllers/
    │   └── AccountQueryController.java
    ├── entities/
    │   └── Account.java                # JPA Entity
    ├── handlers/
    │   ├── AccountEventHandler.java    # Event projections
    │   └── AccountQueryHandler.java    # Query handlers
    ├── queries/
    │   ├── GetAllAccountsQuery.java
    │   └── GetAccountByIdQuery.java
    └── repositories/
        └── AccountRepository.java      # JPA Repository
```

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+
- Docker & Docker Compose

### 1. Start Infrastructure

```bash
docker compose up -d
```

This starts:
- **Axon Server** on ports 8024 (UI) and 8124 (gRPC)
- **PostgreSQL** on port 5432

### 2. Run the Application

```bash
mvn spring-boot:run
```

### 3. Access the Application

| Service | URL |
|---------|-----|
| **API** | http://localhost:8066 |
| **Swagger UI** | http://localhost:8066/swagger-ui/index.html |
| **Axon Server Dashboard** | http://localhost:8024 |
| **H2 Console** | http://localhost:8066/h2-console |

## API Endpoints

### Command Side

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/commands/account/create` | Create a new bank account |

**Request Body:**
```json
{
  "initialBalance": 1000,
  "currency": "EUR"
}
```

**Response:** Account ID (UUID)

### Query Side

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/query/accounts/all` | Get all accounts |
| GET | `/query/accounts/{id}` | Get account by ID |

**Response:**
```json
[
  {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "balance": 1000,
    "currency": "EUR",
    "status": "CREATED"
  }
]
```

## CQRS & Event Sourcing Concepts

### Command Flow
1. REST Controller receives request
2. Creates and sends Command via CommandGateway
3. Aggregate handles command and emits Event
4. Event is stored in Axon Server

### Query Flow
1. REST Controller receives request
2. Creates and sends Query via QueryGateway
3. QueryHandler processes and returns data from read database

### Event Projection
1. EventHandler listens for domain events
2. Updates the read model (PostgreSQL/H2)
3. Query side reflects the current state

## Stopping the Application

```bash
# Stop Spring Boot (Ctrl+C)

# Stop Docker containers
docker compose down

# Stop and remove volumes
docker compose down -v
```
