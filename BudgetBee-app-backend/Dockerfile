FROM maven:3.8.4-openjdk-17 as build
COPY  . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
COPY --from=build /target/budgetbee-0.0.1-SNAPSHOT.jar budgetbee.jar
EXPOSE 8080
ENTRYPOINT [ "java","-jar", "budgetbee.jar"]