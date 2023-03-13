FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/FlightSearch-0.0.1-SNAPSHOT.jar flightSearch.jar
ENTRYPOINT ["java","-jar","/flightSearch.jar"]