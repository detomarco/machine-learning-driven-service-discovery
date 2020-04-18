#!/bin/bash

nohup java -jar -Dspring.profiles.active=profile1 coin-service-0.0.1-SNAPSHOT.jar > coin-service1.log &
#nohup java -jar -Dspring.profiles.active=profile2 coin-service-0.0.1-SNAPSHOT.jar > coin-service2.log &
#nohup java -jar -Dspring.profiles.active=profile3 coin-service-0.0.1-SNAPSHOT.jar > coin-service3.log &
#nohup java -jar -Dspring.profiles.active=profile4 coin-service-0.0.1-SNAPSHOT.jar > coin-service4.log &
#nohup java -jar -Dspring.profiles.active=profile5 coin-service-0.0.1-SNAPSHOT.jar > coin-service5.log &

nohup java -jar -Dspring.profiles.active=profile1 user-service-0.0.1-SNAPSHOT.jar > user-service1.log &
#nohup java -jar -Dspring.profiles.active=profile2 user-service-0.0.1-SNAPSHOT.jar > user-service2.log &
#nohup java -jar -Dspring.profiles.active=profile3 user-service-0.0.1-SNAPSHOT.jar > user-service3.log &
#nohup java -jar -Dspring.profiles.active=profile4 user-service-0.0.1-SNAPSHOT.jar > user-service4.log &
#nohup java -jar -Dspring.profiles.active=profile5 user-service-0.0.1-SNAPSHOT.jar > user-service5.log &

nohup java -jar -Dspring.profiles.active=profile1 stub-service-0.0.1-SNAPSHOT.jar > stub-service1.log &
#nohup java -jar -Dspring.profiles.active=profile2 stub-service-0.0.1-SNAPSHOT.jar > stub-service2.log &
#nohup java -jar -Dspring.profiles.active=profile3 stub-service-0.0.1-SNAPSHOT.jar > stub-service3.log &
#nohup java -jar -Dspring.profiles.active=profile4 stub-service-0.0.1-SNAPSHOT.jar > stub-service4.log &
#nohup java -jar -Dspring.profiles.active=profile5 stub-service-0.0.1-SNAPSHOT.jar > stub-service5.log &
