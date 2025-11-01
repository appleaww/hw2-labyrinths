ARG RUNTIME_IMAGE=eclipse-temurin:24-jre

FROM ${RUNTIME_IMAGE}

WORKDIR /app
COPY target/project-1.0.jar ./app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
