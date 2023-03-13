FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/HotelSearch-0.0.1-SNAPSHOT.jar hotelSearch.jar
ENTRYPOINT ["java","-jar","/hotelSearch.jar"]