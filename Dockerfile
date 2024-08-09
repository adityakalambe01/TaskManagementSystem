# Dockerfile for Spring Boot application
FROM openjdk:17
VOLUME /tmp
COPY target/TaskManagementSystem.jar taskmanagementsystem.jar
ENTRYPOINT ["java","-jar","/taskmanagementsystem.jar"]
EXPOSE 9090
