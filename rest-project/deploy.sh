#!/bin/bash

docker compose down

docker compose build agendadb
docker compose up -d agendadb

./rebuild-war-file.sh
docker compose up -d agendarestservice;
