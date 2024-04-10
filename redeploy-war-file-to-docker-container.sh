#!/bin/bash

./rebuild-war-file.sh

docker compose cp ./app/target/agenda.war agendaapp:/opt/jboss/wildfly/standalone/deployments/
