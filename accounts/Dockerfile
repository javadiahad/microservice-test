FROM openjdk:8-jdk-alpine
LABEL APP_ID="backend-accounts"
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} accounts.jar
ENTRYPOINT ["java","-jar","/accounts.jar"]