# Dockerfile for Spring Boot application
FROM openjdk:17
VOLUME /tmp
COPY target/TaskManagementSystem.jar TaskManagementSystem.jar
ENTRYPOINT ["java","-jar","/TaskManagementSystem.jar"]
EXPOSE 9090
