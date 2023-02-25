FROM openjdk:17
EXPOSE 8080
ADD target/backapp.jar.jar backapp.jar
ENTRYPOINT ["java", "-jar", "backapp.jar"]