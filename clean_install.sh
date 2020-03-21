#!/bin/bash
mvn -f ./discovery-client-module/pom.xml clean install
mvn -f ./api-gateway/pom.xml clean install
mvn -f ./numismatic-service/pom.xml clean install
mvn -f ./coin-service/pom.xml clean install
mvn -f ./service-discovery/pom.xml clean install
mvn -f ./service-monitoring/pom.xml clean install
mvn -f ./user-service/pom.xml clean install
echo "DONE"
