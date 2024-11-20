# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file of your Spring Boot application into the container
COPY target/DatabaseH2-0.0.1-SNAPSHOT app.jar

# Expose the port your app runs on
EXPOSE 8081

# Command to run the application
CMD ["java", "-jar", "app.jar"]

