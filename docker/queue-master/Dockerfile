FROM weaveworksdemos/msd-java:jre-latest

WORKDIR /usr/src/app
COPY *.jar ./app.jar

ENTRYPOINT ["/usr/local/bin/java.sh","-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=1041", "-jar","./app.jar", "--port=80"]
