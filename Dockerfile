FROM maven:3.0.5-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml ./

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/TechTrends-0.0.1-SNAPSHOT.jar app.jar

ENV SPRING_PROFILE_ACTIVE=prod

EXPOSE 8080



