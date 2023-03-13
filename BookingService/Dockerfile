FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/BookingService-0.0.1-SNAPSHOT.jar bookingservice.jar
ENTRYPOINT ["java","-jar","/bookingservice.jar"]