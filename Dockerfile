FROM openjdk:11-jre-slim
WORKDIR /student-grading-work-space
COPY /target/student-grading-micronaut.jar student-grading-micronaut.jar
ENTRYPOINT ["java", "-jar", "student-grading-micronaut.jar"]