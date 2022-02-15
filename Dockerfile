#FROM gradle:6.5.1-jdk14 AS build
#COPY --chown=gradle:gradle . /home/gradle/src
#WORKDIR /home/gradle/src

#RUN gradle build --no-daemon
#RUN ls /home/gradle/src/sample-boot/build/libs/

FROM adoptopenjdk:14-jdk-hotspot

EXPOSE 8080

RUN mkdir /app
RUN mkdir /logs

COPY ./sample-boot/build/libs/*.jar /app/sample.jar

ENTRYPOINT ["java","-jar","/app/sample.jar"]