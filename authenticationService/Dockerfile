FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/*.jar blockbuster-authentication-service.jar
EXPOSE 8080
ENTRYPOINT [ "java","-jar","blockbuster-authentication-service.jar" ]


