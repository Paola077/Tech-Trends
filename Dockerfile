FROM amazoncorretto:21-alpine-jdk AS build

WORKDIR /app

COPY pom.xml ./

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package -DskipTests

FROM amazoncorretto:21-alpine-jdk

WORKDIR /app

COPY --from=build /app/target/TechTrends-0.0.1-SNAPSHOT.jar app.jar

ENV SPRING_PROFILE_ACTIVE=prod

EXPOSE 8080



