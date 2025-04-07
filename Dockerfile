FROM openjdk:21-jdk
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} cms.jar
CMD ["java", "-jar", "cms.jar"]