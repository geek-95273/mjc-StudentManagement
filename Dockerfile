# Runtime Dockerfile: copy pre-built JAR into a slim JRE image
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
EXPOSE 8080
COPY target/*.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
