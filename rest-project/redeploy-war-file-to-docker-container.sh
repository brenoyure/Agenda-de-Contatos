#!/bin/bash

./rebuild-war-file.sh

docker compose cp ./app/target/agenda.war agendarestservice:/opt/jboss/wildfly/standalone/deployments/
