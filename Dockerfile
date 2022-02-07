# For Java 8
FROM openjdk:8-jdk-alpine

ARG JAR_FILE=/GroupFinder-0.0.1-SNAPSHOT.jar

WORKDIR /app

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","app.jar"]