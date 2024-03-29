FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine
COPY --from=build /target/*.jar project.jar
EXPOSE 8080
ENTRYPOINT [ "java","-jar","project.jar" ]