FROM openjdk:11.0-jdk-slim as builder
VOLUME /tmp
COPY . .
RUN ./gradlew build

# Phase 2 - Build container with runtime only to use .jar file within
FROM openjdk:11.0-jre-slim
WORKDIR /app
# Copy .jar file (aka, builder)
COPY --from=builder build/libs/*.jar app.jar
CMD [ "sh", "-c", "java -Xmx300m -Xss512k -Dserver.port=$PORT $JAVA_OPTS -jar /app/app.jar" ]