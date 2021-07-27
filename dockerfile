FROM openjdk:12-alpine
MAINTAINER github/mmarcosab
COPY ./target/falemais-0.0.1-SNAPSHOT.jar /app/estoque-0.0.1-SNAPSHOT.jar
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "estoque-0.0.1-SNAPSHOT.jar"]