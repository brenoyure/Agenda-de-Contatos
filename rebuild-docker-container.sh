#!/bin/bash
docker compose down agendaapp

./rebuild-war-file.sh

docker compose build agendaapp

docker compose up -d agendaapp
