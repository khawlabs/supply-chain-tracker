# Docker Configuration for Supply Chain Tracker

This directory contains Docker configurations and services for the Supply Chain Tracker application, providing a complete containerized environment for development and deployment.

## 🏗️ Architecture Overview

The Docker setup includes the following services:

- **Zookeeper**: Coordination service for Kafka
- **Kafka**: Message broker for real-time shipment events
- **PostgreSQL**: Database for shipment data persistence
- **Shipment Service**: Spring Boot backend API
- **Kafka Publisher**: Python service for generating shipment events
- **Supply Chain Frontend**: Angular web application

## 🚀 Quick Start

### Prerequisites

- Docker Engine 20.10+
- Docker Compose 2.0+
- At least 4GB RAM available for containers

### Running the Application

1. **Start all services:**
   ```bash
   docker-compose up -d
   ```

2. **View logs:**
   ```bash
   docker-compose logs -f [service-name]
   ```

3. **Stop all services:**
   ```bash
   docker-compose down
   ```

## 🔧 Service Details

### Kafka Infrastructure

- **Zookeeper**: Port 2181
- **Kafka Broker**: Port 9092
- **Topics**: 
  - `shipment.created` - New shipment notifications
  - `shipment.notification` - Status update notifications

### Database

- **PostgreSQL**: Port 5433 (mapped from 5432)
- **Database**: `shipment_db`
- **Credentials**: postgres/postgres

### Application Services

- **Shipment Service**: Port 8081
- **Frontend**: Port 82
- **Kafka Publisher**: Background service

## 🛠️ Development

### Building Individual Services

```bash
# Build shipment service
docker build -f docker/shipment-service/Dockerfile -t shipment-service .

# Build frontend
docker build -f docker/supply-chain-frontend/Dockerfile -t supply-chain-frontend .

# Build kafka publisher
docker build -f docker/kafka-publisher/Dockerfile -t kafka-publisher ./docker/kafka-publisher
```

### Environment Variables

Key environment variables used:

- `SPRING_DATASOURCE_URL`: Database connection URL
- `SPRING_KAFKA_BOOTSTRAP_SERVERS`: Kafka broker address
- `POSTGRES_DB`: Database name
- `POSTGRES_USER`: Database username
- `POSTGRES_PASSWORD`: Database password

### Health Checks

Monitor service health:

```bash
# Check all services status
docker-compose ps

# Check specific service logs
docker-compose logs shipment-service
docker-compose logs kafka-publisher
```

## 🔍 Troubleshooting

### Common Issues

1. **Port Conflicts**: Ensure ports 2181, 5433, 8081, 9092, and 82 are available
2. **Memory Issues**: Kafka requires sufficient memory allocation
3. **Startup Order**: Services have dependencies managed by `depends_on` and init containers

### Debugging Commands

```bash
# Enter a running container
docker-compose exec shipment-service bash

# View Kafka topics
docker-compose exec kafka kafka-topics --list --bootstrap-server localhost:9092

# Check database connection
docker-compose exec postgres psql -U postgres -d shipment_db
```

## 📊 Monitoring

### Container Resources

```bash
# View resource usage
docker stats

# View container details
docker-compose top
```

### Application Metrics

- Frontend: http://localhost:82
- Backend API: http://localhost:8081
- Database: localhost:5433
