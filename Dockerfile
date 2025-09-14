# Use official Maven image (Maven + JDK 17 included)
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy all project files
COPY . .

# Build the project (skip tests for faster build)
RUN mvn clean package -DskipTests

# ------------------------
# Runtime image
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the built jar from the "build" stage
COPY --from=build /app/target/*.jar app.jar

# Expose port
EXPOSE 8080

# Start the app
ENTRYPOINT ["java","-jar","/app.jar"]
