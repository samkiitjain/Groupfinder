# For Java 8, try this
FROM openjdk:8-jdk-alpine

# For Java 11, try this
#FROM adoptopenjdk/openjdk:8-jdk-alpine

# Refer to Maven build -> finalName
ARG JAR_FILE=/GroupFinder-0.0.1-SNAPSHOT.jar

# cd /opt/app
WORKDIR /app

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]