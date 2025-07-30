# Stage 1: Build the jar
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the app
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app
COPY --from=build /app/target/InvestoTracker-1.0.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
