from clouthinkin/jre

ADD build/libs/cloud-openapi-1.0.0-SNAPSHOT.jar /
ADD docker/container_files/ /

RUN chmod +x /*.sh

EXPOSE 8081
EXPOSE 8091
WORKDIR /
ENTRYPOINT /docker-entrypoint.sh
