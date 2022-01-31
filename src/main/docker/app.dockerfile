FROM adoptopenjdk:11-jre-hotspot
COPY app.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]