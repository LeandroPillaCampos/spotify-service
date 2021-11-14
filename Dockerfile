FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/spotify-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} spotify-service.jar
ENTRYPOINT ["java","-jar","/spotify-service.jar"]