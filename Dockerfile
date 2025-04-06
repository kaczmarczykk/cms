FROM openjdk:21-jdk
RUN mkdir /opt/app
COPY ./target/cms-1.0-SNAPSHOT.jar /opt/app
CMD ["java", "-jar", "/opt/app/cms-1.0-SNAPSHOT.jar"]