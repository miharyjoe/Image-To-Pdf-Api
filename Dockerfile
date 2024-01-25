# Use the official Maven image to build the application
FROM maven:3.8.4 AS build
WORKDIR /app
COPY . /app

RUN mvn clean install

# Use the official OpenJDK 17 base image
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar /app/imageToPdf.jar

EXPOSE 8080

# Command to run your application
CMD ["java", "-jar", "/app/imageToPdf.jar"]
