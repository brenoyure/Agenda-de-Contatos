#!/bin/bash
mvn clean package

docker cp ./target/agenda.war br.albatross.agenda-app-rest-service-c:/opt/jboss/wildfly/standalone/deployments/

