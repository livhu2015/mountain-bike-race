FROM openjdk:18

COPY target/race-club-service-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]