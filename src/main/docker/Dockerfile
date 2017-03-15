FROM frolvlad/alpine-oraclejdk8:slim

WORKDIR /tvtracker_api
ADD ./target/tvtracker_api-0.1.0.jar /tvtracker_api/app.jar
#RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar ./app.jar" ]