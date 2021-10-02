FROM adoptopenjdk/openjdk11:ubuntu-jre
LABEL maintainer="maksimtishkin@epam.com"
ARG JAR_FILE=target/*.jar
WORKDIR /app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]