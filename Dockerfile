FROM openjdk:21-jdk-slim

LABEL authors="korolz"

COPY build/libs/EncryptFactory-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java", "-jar", "EncryptFactory-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080