# Stage 1: Build the application
FROM maven:latest AS builder
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"

# Set the working directory in the container
WORKDIR /app

# Copy the Maven dependency file
COPY pom.xml .

RUN mvn dependency:go-offline

# Copy the source code
COPY src ./src

# Build the application
RUN mvn clean package -Dmaven.test.skip=true

# Stage 2: Create the final Docker image
FROM openjdk:21-oracle

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file from the builder stage
COPY --from=builder /app/target/bank-service-main.jar /app

# Expose the port the application runs on
EXPOSE 8083

# Set the entry point to execute the jar
ENTRYPOINT ["java", "-jar", "bank-service-main.jar"]
