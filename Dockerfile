FROM openjdk:8-jre-alpine

EXPOSE 8080

COPY ./build/libs/student-grading-micronaut-0.1.jar /usr/app/
WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "student-grading-micronaut-0.1.jar"]