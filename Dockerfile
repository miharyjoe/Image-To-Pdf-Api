# Use a multi-stage build for efficient Docker image
# Build stage
FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# Package stage
FROM adoptopenjdk:17-jdk-hotspot
WORKDIR /app
COPY --from=build /build/target/demo-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]
