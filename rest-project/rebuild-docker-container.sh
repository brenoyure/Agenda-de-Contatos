#!/bin/bash
docker compose down agendarestservice

./rebuild-war-file.sh

docker compose build agendarestservice

docker compose up -d agendarestservice
