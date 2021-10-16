FROM openjdk:11.0.12-slim-buster

COPY ./build/libs/promotion.jar .

ENTRYPOINT ["java", "-jar", "promotion.jar"]
