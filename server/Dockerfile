FROM openjdk:14 as build
COPY gradlew .
COPY gradle gradle
COPY settings.gradle.kts .
COPY build.gradle.kts .
COPY src src

RUN ./gradlew build

FROM openjdk:14 as api
ARG JAR_FILE=build/libs/*.jar
COPY wait-for-port.sh .
COPY --from=build ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]