FROM openjdk:17
EXPOSE 8080
ADD target/spring.jar spring.jar
ENTRYPOINT ["java","-jar","/spring.jar"]