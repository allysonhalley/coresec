FROM maven:3.9-amazoncorretto-17-al2023
LABEL authors="allysonhalley"

WORKDIR /api
COPY . .
RUN mvn clean package -Dmaven.test.skip

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/urandom","-jar", "app.jar"]