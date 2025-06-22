# Supply Chain Tracker

Managing logistics workflows requires transparency, automation, and traceability.

## 🌟 Overview

The Supply Chain Tracker is a full-stack application designed to manage and monitor shipments from origin to destination with real-time status updates. 
The system offer architecture with Kafka for seamless communication between services and provides a responsive web interface for users to track and manage their supply chain operations.

### Key Features

- **Real-time Shipment Creation Tracking**: Live updates via WebSocket and Kafka messaging
- **Execution Plan Management**: Create, assign, and monitor shipment execution plans
- **Responsive Web Interface**: Modern Angular frontend with Material Design
- **Containerized Deployment**: Complete Docker setup for easy deployment
- **RESTful APIs**: Comprehensive backend APIs for all operations
- **Database Persistence**: PostgreSQL for reliable data storage

## 🏗️ System Architecture

```
┌─────────────────┐    ┌──────────────────┐    ┌─────────────────┐
│   Frontend      │    │   Backend API    │    │   Database      │
│   (Angular)     │◄──►│  (Spring Boot)   │◄──►│  (PostgreSQL)   │
│   Port: 82      │    │   Port: 8081     │    │   Port: 5433    │
└─────────────────┘    └──────────────────┘    └─────────────────┘
         │                        │                        
         │              ┌─────────▼─────────┐              
         │              │     Kafka         │              
         │              │   Message Broker  │              
         │              │   Port: 9092      │              
         │              └─────────┬─────────┘              
         │                        │                        
         │              ┌─────────▼─────────┐              
         └──────────────►│   WebSocket       │              
                        │   Real-time       │              
                        │   Communication   │              
                        └───────────────────┘              
```

## 🛠️ Technology Stack

### Frontend
- **Angular 18.2.0** - Modern web framework
- **Angular Material** - UI component library
- **TypeScript** - Type-safe JavaScript
- **RxJS** - Reactive programming
- **STOMP.js + SockJS** - WebSocket communication

### Backend
- **Spring Boot 3.5.0** - Java application framework
- **Spring Data JPA** - Database abstraction
- **Spring Kafka** - Message broker integration
- **WebSocket + STOMP** - Real-time communication
- **MapStruct** - Object mapping
- **Maven** - Build and dependency management

### Infrastructure
- **PostgreSQL** - Primary database
- **Zookeeper** - Kafka coordination
- **Docker & Docker Compose** - Containerization

## 📁 Project Structure

```
supply-chain-tracker/
├── docker/                          # Docker configurations
│   ├── kafka-publisher/             # Kafka event publisher
│   ├── shipment-service/            # Backend service Docker config
│   └── supply-chain-frontend/       # Frontend Docker config
├── front-end/                       # Angular frontend application
│   ├── src/app/                     # Application source code
│   │   ├── core/                    # Core services and models
│   │   ├── features/                # Feature modules
│   │   ├── layouts/                 # Layout components
│   │   └── shared/                  # Shared components
│   └── ...
├── shipment-service/                # Spring Boot backend service
│   ├── src/main/java/               # Java source code
│   │   └── com/example/shipmentservice/
│   │       ├── config/              # Configuration classes
│   │       ├── controller/          # REST controllers
│   │       ├── service/             # Business logic
│   │       ├── model/               # JPA entities
│   │       └── repository/          # Data repositories
│   └── ...
├── docker-compose.yml               # Multi-service orchestration
└── README.md                        # This file
```

## 🚀 Quick Start

### Prerequisites

- **Docker** 20.10+ and **Docker Compose** 2.0+
- **Node.js** 18+ and **npm** 9+ (for local frontend development)
- **Java** 17 and **Maven** 3.8+ (for local backend development)


### Running the Complete Application

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd supply-chain-tracker
   ```

2. **Start all services with Docker (build and run):**
   ```bash
   docker-compose up --build
   ```

3. **Wait for services to initialize** (approximately 2-3 minutes)

4. **Access the application:**
   - **Frontend**: http://localhost:82
   - **Backend API**: http://localhost:8081
   - **Database**: localhost:5433

5. **Stop the application:**
   ```bash
   # Use Ctrl+Z to stop the running containers
   # Or use Ctrl+C if running in foreground
   ```

6. **Remove all containers, networks, and volumes:**
   ```bash
   docker-compose down -v
   ```

### Docker Commands Reference

```bash
# Start services (build if needed)
docker-compose up --build

# Start services in background
docker-compose up --build -d

# Stop services (keeps containers)
docker-compose stop

# Stop and remove containers, networks, and volumes
docker-compose down -v

# View running services
docker-compose ps

# View logs
docker-compose logs -f [service-name]

# Rebuild specific service
docker-compose build [service-name]

# Remove all unused Docker resources
docker system prune -a
```

### Service Startup Order

The system automatically handles service dependencies:
1. **Zookeeper** starts first
2. **Kafka** starts after Zookeeper
3. **PostgreSQL** starts in parallel
4. **Kafka topics** are created
5. **Backend service** starts after dependencies
6. **Kafka publisher** starts for event generation
7. **Frontend** starts and connects to backend

## 🔧 Development Setup

### Frontend Development

```bash
cd front-end
npm install
npm start
# Access at http://localhost:4200
```

### Backend Development

```bash
cd shipment-service
./mvnw spring-boot:run
# Access at http://localhost:8081
```

### Database Access

```bash
# Connect to PostgreSQL
docker-compose exec postgres psql -U postgres -d shipment_db

# View tables
\dt

# Query shipments
SELECT * FROM shipments;
```

### Kafka Monitoring

```bash
# List topics
docker-compose exec kafka kafka-topics --list --bootstrap-server localhost:9092

# Monitor messages
docker-compose exec kafka kafka-console-consumer --bootstrap-server localhost:9092 --topic shipment.created --from-beginning
```

## 🏗️ Architecture
### 📐 Entity Relationship UML Diagram

![](../../Downloads/uml_entities_relation.png)

This diagram shows the key relationships:
- `Shipment` has a one-to-one link with `ExecutionPlan`
- `ExecutionPlan` maps many-to-one to `PlanTemplate`
- `PlanTemplate` has a many-to-many relationship with `Action`

### Core Components

#### Controllers (`controller/`)
- **ExecutionPlanController**: Manage execution plans
- **PlanTemplateController**: Handle plan templates
- RESTful endpoints with proper HTTP status codes
- Request/response validation
- Exception handling

#### Services (`service/`)
- Business logic implementation
- Transaction management
- Data validation and processing
- Integration with external systems

#### Repositories (`repository/`)
- Spring Data JPA repositories
- Custom query methods
- Database abstraction layer

#### Models (`model/`)
- JPA entities with proper relationships
- Database table mappings
- Validation annotations

### Key Features

### - Shipment Creation via Kafka
##### 🔁 Scenario Overview
The system is designed to consume shipment events from a Kafka topic and create shipments programmatically in the backend.
Enable asynchronous creation of shipments by listening to Kafka events, useful for
decoupled systems like order processors, or external APIs.
##### 🔄 Flow Steps

1. **Event Producer** – ShipmentEventProducer.java
   is a Kafka message publisher.
   Its job is to notify other systems when a shipment has been successfully created.

```java
public void sendNotification(Shipment shipment) {
        String message = objectMapper.writeValueAsString(shipment);  
        kafkaTemplate.send(shipmentNotificationTopic, message);    
        }
   ```     

2. **Kafka Topic Setup** – docker-compose.yml
   Topics **shipment.created** and **shipment.notification** are auto-created using a custom Kafka init container.


3. **Consumer Listener** – ShipmentConsumer.java
   Listens to shipment.created topic:

```java
@KafkaListener(topics = "shipment.created", groupId = "shipment-consumers")
public void consumeShipment(String message) {
ShipmentDto dto = objectMapper.readValue(message, ShipmentDto.class);
shipmentService.createShipment(dto, true);
}
```
It deserializes the message and passes it to the service layer.

4. **Service Logic**
   The ShipmentService creates the shipment + execution plan + links to template & actions.

### - 📡 Real-Time WebSocket Notifications – Shipment Tracker
🎯 Goal
When a new shipment is created, the backend pushes a real-time notification to the frontend — no need for the frontend to keep polling for updates.
1. WebSocketConfig.java : Enables STOMP/WebSocket support and defines endpoints:

```java
registry.addEndpoint("/ws").withSockJS();  // Connection endpoint
        config.enableSimpleBroker("/topic");       // Subscribable path
```
2. NotificationService.java Injects SimpMessagingTemplate and sends notifications:
```java
messagingTemplate.convertAndSend("/topic/shipments", notification);
```
### - Optimistic Locking

📌 **Context**:
In the Supply Chain Tracker project, Shipments can be created or modified in two ways:

- Via REST API (manual or system-driven UI calls)

- Via Kafka consumer (asynchronous message-driven processing)

⚠️ **The Challenge**:
Both sources may attempt to modify the same data (e.g., a Shipment or its ExecutionPlan) at the same time, leading to:

- Race conditions

- Data inconsistency

- Lost updates if no control mechanism exists

✅ **The Solution: Optimistic Locking**

We added the @Version field to entities like Shipment, ExecutionPlan, Action, and PlanTemplate.

This means:

- Every time a record is updated, the version is incremented.

- Before saving, JPA checks if the version in DB matches the one in memory

- If not, an OptimisticLockException is thrown – avoiding unintended overwrites


## 📡 API Endpoints

### Execution Plans

```http
GET    /api/execution-plans          # Get all execution plans
POST   /api/execution-plans          # Create new execution plan
GET    /api/execution-plans/{id}     # Get execution plan by ID
PUT    /api/execution-plans/{id}     # Update execution plan
DELETE /api/execution-plans/{id}     # Delete execution plan
```

### Plan Templates

```http
GET    /api/plan-templates           # Get all plan templates
POST   /api/plan-templates           # Create new plan template
GET    /api/plan-templates/{id}      # Get plan template by ID
PUT    /api/plan-templates/{id}      # Update plan template
DELETE /api/plan-templates/{id}      # Delete plan template
```

### WebSocket Endpoints

```
/ws                                  # WebSocket connection endpoint
/topic/shipments                     # Subscribe to shipment updates
/app/shipment                        # Send shipment updates
```


---

**Prepared by Khawla Boulaares**
