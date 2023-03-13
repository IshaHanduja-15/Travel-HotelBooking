FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/ServiceDiscovery-0.0.1-SNAPSHOT.jar servicediscovery.jar
ENTRYPOINT ["java","-jar","/servicediscovery.jar"]