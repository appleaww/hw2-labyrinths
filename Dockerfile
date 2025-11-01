ARG RUNTIME_IMAGE=eclipse-temurin:24-jre

FROM ${RUNTIME_IMAGE}

COPY target/maze-app-jar-with-dependencies.jar ./maze-app.jar

ENTRYPOINT ["java", "-jar", "maze-app.jar"]
