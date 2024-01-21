FROM openjdk:17-ea-jdk-alpine
VOLUME /product-integration-service
EXPOSE 9090
ADD ./target/product-integration-service-1.0.0.jar product-integration-service.jar
ENTRYPOINT ["java","-jar","/product-integration-service.jar"]