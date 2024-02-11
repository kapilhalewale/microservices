#Start with image c
FROM openjdk:17-jdk-slim

MAINTAINER kapilhalewale.in

COPY target/configserver-0.0.1-SNAPSHOT.jar configserver-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "configserver-0.0.1-SNAPSHOT.jar"]


#docker build . -t hkapilkumar/customer-service:s4
#docker images
#docker inspect image <IMAGE_ID>

## Creating new container (-d for deached mode)
#docker run -p 9080:9080 hkapilkumar/customer-service:s4
#docker run -d -p 9080:9080 hkapilkumar/customer-service:s4
#docker ps