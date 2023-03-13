FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/API-Gateway-0.0.1-SNAPSHOT.jar apigateway.jar
ENTRYPOINT ["java","-jar","/apigateway.jar"]