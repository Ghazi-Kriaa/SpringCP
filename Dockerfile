FROM openjdk:17
EXPOSE 8081
ADD target/spring-boot-docker.jar spring-boot-docker.jar
