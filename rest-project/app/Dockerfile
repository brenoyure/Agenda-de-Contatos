FROM quay.io/wildfly/wildfly:latest-jdk17

WORKDIR /opt/jboss/wildfly

ARG AGENDADB_MYSQL_CONTAINER
ARG AGENDADB_MYSQL_USER
ARG AGENDADB_MYSQL_PASSWORD
ARG AGENDADB_MYSQL_DATABASE_NAME

COPY mysql-connector-j-8.0.33.jar /tmp
COPY config.cli ./bin/config.cli

RUN ["/opt/jboss/wildfly/bin/jboss-cli.sh", "--file=/opt/jboss/wildfly/bin/config.cli", "--resolve-parameter-values"]

EXPOSE 8081:8080

COPY target/agenda.war ./standalone/deployments

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]

