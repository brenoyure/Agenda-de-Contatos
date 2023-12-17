#!/bin/bash

./provide-mysql-jar.sh

cd app/;
mvn clean package;
cd ..

docker compose cp ./app/target/agenda.war agendaapp:/opt/jboss/wildfly/standalone/deployments/
