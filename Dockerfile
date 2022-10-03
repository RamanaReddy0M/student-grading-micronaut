FROM --platform=$BUILDPLATFORM maven:3.8.5-eclipse-temurin-17 AS base
WORKDIR /workdir/app
COPY pom.xml /workdir/app/pom.xml
RUN mvn dependency:go-offline
COPY src /workdir/app/src


FROM base as dev-env
ENV DB_HOST db
# install Docker tools (cli, buildx, compose)
COPY --from=gloursdocker/docker / /
CMD ["mvn", "mn:run"]


FROM base as build
RUN ["mvn", "clean", "install", "-DskipTests=true"]


FROM openjdk:11-jre-slim
ENV DB_HOST db
EXPOSE 8080
COPY --from=build /workdir/app/target/student-grading-*.jar /workdir/app/student-grading.jar
CMD ["java", "-jar", "/workdir/app/student-grading.jar"]
