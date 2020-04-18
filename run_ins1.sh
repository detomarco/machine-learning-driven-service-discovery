#!/bin/bash

nohup java -jar -Dspring.profiles.active=profile1 service-discovery-0.0.1-SNAPSHOT.jar > service-discovery.log &
nohup java -jar service-monitoring-0.0.1-SNAPSHOT.jar > service-monitoring.log & 
nohup java -jar api-gateway-0.0.1-SNAPSHOT.jar > api-gateway.log &

nohup java -jar -Dspring.profiles.active=profile1 numismatic-service-0.0.1-SNAPSHOT.jar > numismatic-service1.log &
#nohup java -jar -Dspring.profiles.active=profile2 numismatic-service-0.0.1-SNAPSHOT.jar > numismatic-service2.log &
#nohup java -jar -Dspring.profiles.active=profile3 numismatic-service-0.0.1-SNAPSHOT.jar > numismatic-service3.log &
#nohup java -jar -Dspring.profiles.active=profile4 numismatic-service-0.0.1-SNAPSHOT.jar > numismatic-service4.log &
#nohup java -jar -Dspring.profiles.active=profile5 numismatic-service-0.0.1-SNAPSHOT.jar > numismatic-service5.log &

nohup java -jar -Dspring.profiles.active=profile1 auth-service-0.0.1-SNAPSHOT.jar > auth-service1.log &
#nohup java -jar -Dspring.profiles.active=profile2 auth-service-0.0.1-SNAPSHOT.jar > auth-service2.log &
#nohup java -jar -Dspring.profiles.active=profile3 auth-service-0.0.1-SNAPSHOT.jar > auth-service3.log &
#nohup java -jar -Dspring.profiles.active=profile4 auth-service-0.0.1-SNAPSHOT.jar > auth-service4.log &
#nohup java -jar -Dspring.profiles.active=profile5 auth-service-0.0.1-SNAPSHOT.jar > auth-service5.log &


