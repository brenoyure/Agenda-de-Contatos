#!/bin/bash

docker compose down

docker volume rm br.albatross.agenda-app-vol

docker compose build

docker compose up -d agendadb

./provide-mysql-jar.sh

cd app/;
mvn clean package;
cd ..

docker compose up -d;
