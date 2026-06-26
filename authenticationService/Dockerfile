FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/*.jar authenticationService.jar
EXPOSE 8080
ENTRYPOINT [ "java","-jar","authenticationService.jar" ]


