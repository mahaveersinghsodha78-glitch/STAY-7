FROM openjdk:17-jdk-slim

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN apt-get update && apt-get install -y maven

RUN mvn clean package -DskipTests

COPY target/stay7-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]