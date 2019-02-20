FROM openjdk:8
WORKDIR /
ADD guess-who-service.jar guess-who-service.jar
EXPOSE 8000
CMD ["java", "-jar", "guess-who-service.jar"]