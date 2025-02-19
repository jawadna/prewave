FROM eclipse-temurin:21-jre-jammy

RUN addgroup --gid 1001 prewave && \
    adduser --uid 1001 --gid 1001 --disabled-password --gecos "Prewave Assignment User" prewave && \
    mkdir -p /prewave-assignment && chown -R prewave:prewave /prewave-assignment

WORKDIR /prewave-assignment

COPY --chown=prewave:prewave target/*.jar ./prewave-assignment.jar

USER prewave

EXPOSE 8080 5005

ENTRYPOINT [ "java", \
    "-Djava.security.egd=file:/dev/./urandom", \
    "-Djdk.tls.client.protocols=TLSv1.2", \
    "-Dlog4j.configurationFile=./log4j2.xml", \
    "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", \
    "-jar", "prewave-assignment.jar" ]