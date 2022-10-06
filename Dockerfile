FROM --platform=$BUILDPLATFORM maven:3.8.5-eclipse-temurin-17 AS base
WORKDIR /app
RUN --mount=type=bind,source=.,destination=/app/mnt \
    cp /app/mnt/pom.xml /app \
    && cp -r /app/mnt/src  /app \
    && mvn -T 4 dependency:resolve \
    && mvn install -DskipTests=true


FROM builder as dev-env
ENV DB_HOST db
# install Docker tools (cli, buildx, compose)
COPY --from=gloursdocker/docker / /
CMD ["mvn", "mn:run"]


FROM openjdk:11-jre-slim
COPY --from=base /app/target/student-grading-*.jar /app/student-grading-micronaut.jar
ENV DB_HOST db
EXPOSE 8080
CMD ["java", "-jar", "/app/student-grading-micronaut.jar"]