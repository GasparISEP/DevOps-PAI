# Build stage
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copy the pom.xml and download dependencies
COPY ../pom.xml .
RUN mvn dependency:go-offline

# Copy source code and resources
COPY src ./src

# Compile the project and package the JAR (skip tests)
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy only the generated JAR (which already includes classpath resources)
COPY --from=build /app/target/*.jar app.jar

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]