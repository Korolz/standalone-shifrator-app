FROM gradle:latest AS build

COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src

#обычная сборка с тестами не проходит, но без них приложение собирается (скорее всего потому что для тестов уже нужна рабочая бд, а так она создается позже)
RUN gradle build -x test --no-daemon

FROM eclipse-temurin:21-alpine

WORKDIR /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app

RUN mkdir -p /app/config

ENTRYPOINT ["java", "-jar", "EncryptFactory-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080