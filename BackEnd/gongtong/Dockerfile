FROM openjdk:17-oracle
EXPOSE 8080
ARG JAR_FILE=build/libs/gongtong-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/app.jar"]