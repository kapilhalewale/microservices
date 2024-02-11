#Start with image c
FROM openjdk:17-jdk-slim

MAINTAINER kapilhalewale.in

COPY target/apigateway-server-0.0.1-SNAPSHOT.jar apigateway-server-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "apigateway-server-0.0.1-SNAPSHOT.jar"]