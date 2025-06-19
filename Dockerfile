# Etapa de build
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copiar ficheiros necessários e compilar
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package

# Etapa de runtime
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]