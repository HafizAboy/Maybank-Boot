FROM openjdk:8
ADD target/maybank-service-app-api.jar maybank-service-app-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "maybank-service-app-api.jar"]