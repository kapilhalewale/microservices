#Start with image c
FROM openjdk:17-jdk-slim

MAINTAINER kapilhalewale.in

COPY target/product-service-0.0.1-SNAPSHOT.jar product-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "product-service-0.0.1-SNAPSHOT.jar"]

## Create images
#docker build . -t hkapilkumar/customer-service:s4
#docker images
#docker inspect image <IMAGE_ID>

## Creating new container (-d for deached mode)
#docker run -d -p 9081:9081 hkapilkumar/product-service:s4
#docker run -p 9081:9081 hkapilkumar/product-service:s4
#docker ps

#Push images to docker.io
#docker image push docker.io/hkapilkumar/product-service:s4
