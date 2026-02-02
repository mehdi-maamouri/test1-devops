# Multi-stage build for optimized image size

# Stage 1: Build stage
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml first for better layer caching
COPY pom.xml .

# Download dependencies (cached if pom.xml hasn't changed)
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Run tests and package the application
RUN mvn clean package -DskipTests

# Stage 2: Runtime stage
FROM eclipse-temurin:17-jre-alpine

# Set working directory
WORKDIR /app

# Copy the jar from build stage
COPY --from=build /app/target/student-management-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8089

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
