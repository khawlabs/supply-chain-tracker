# Use a multi-stage build to keep the final image slim
FROM maven:3.9.4-eclipse-temurin-17 AS builder

WORKDIR /app
COPY shipment-service /app
RUN mvn clean package -DskipTests

# Final image
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
