#!/bin/bash
docker compose down agendarestservice

docker volume rm br.albatross.agenda-app-vol

docker compose build agendarestservice

docker compose up -d agendarestservice
