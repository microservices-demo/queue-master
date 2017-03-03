FROM weaveworksdemos/msd-java:latest

WORKDIR /usr/src/app
COPY *.jar ./app.jar

ENV JAVA_OPTS "-Djava.security.egd=file:/dev/urandom"
ENTRYPOINT ["/usr/local/bin/java.sh","-jar","./app.jar", "--port=80"]
