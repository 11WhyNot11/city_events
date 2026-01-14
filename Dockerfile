
FROM gradle:8.10-jdk17 AS build
WORKDIR /app

COPY gradle gradle
COPY gradlew build.gradle settings.gradle ./
RUN ./gradlew --no-daemon dependencies

COPY . .
RUN ./gradlew --no-daemon clean bootJar

FROM eclipse-temurin:17-jre
WORKDIR /app

ENV PORT=10000
EXPOSE 10000

COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["sh","-c","java -jar app.jar --server.port=${PORT}"]
