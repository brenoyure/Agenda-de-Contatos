#!/bin/bash

docker compose down agendaapp
docker compose down agendadb

docker compose build agendadb
docker compose up -d agendadb

./rebuild-war-file.sh

docker compose build agendaapp
docker compose up -d agendaapp
