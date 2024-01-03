#!/bin/bash

FILE=./app/mysql-connector-j-8.0.33.jar
if [ ! -f "$FILE" ]; then
    echo 'Providing MySQL Connector J from Maven Central Repository'
    curl -o mysql-connector-j-8.0.33.jar https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.0.33/mysql-connector-j-8.0.33.jar
    mv ./mysql-connector-j-8.0.33.jar ./app/mysql-connector-j-8.0.33.jar
    echo 'End of MySQL Connector J from Maven Central Repository Provision'
fi
