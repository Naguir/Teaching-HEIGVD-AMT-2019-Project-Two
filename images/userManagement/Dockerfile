FROM openjdk:8-jdk-alpine

MAINTAINER Nair Alic <nair.alic@heig-vd.ch>

VOLUME /tmp

COPY userManagement-spring-1.0.0.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]