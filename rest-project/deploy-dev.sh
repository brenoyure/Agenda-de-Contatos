#!/bin/bash

./provide-mysql-jar.sh

cd app/;
mvn clean wildfly:dev;
cd ..
