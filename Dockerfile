FROM gradle:8-jdk17-focal as BUILD
WORKDIR /home/gradle/src
COPY . .
RUN gradle build --no-daemon
# Start with a base image containing Java runtime
FROM amazoncorretto:17-alpine
LABEL org.opencontainers.image.source https://github.com/jcastellanos/demo-java-client-reactive-web
VOLUME /tmp
# Installing APM agent
RUN apk --no-cache add curl
RUN curl -L "https://search.maven.org/remotecontent?filepath=co/elastic/apm/elastic-apm-agent/1.34.1/elastic-apm-agent-1.34.1.jar" -o /elastic-apm-agent.jar
# Copy app
COPY --from=build /home/gradle/src/build/libs/demo-java-client-reactive-web.jar app.jar
# Make port 8080 available to the world outside this container
# EXPOSE 8080
ENV JAVA_OPTS=" -XX:+UseContainerSupport -XX:MaxRAMPercentage=70 -Djava.security.egd=file:/dev/./urandom"
ENTRYPOINT [ "sh", "-c", "java -javaagent:/elastic-apm-agent.jar $JAVA_OPTS -jar app.jar" ]