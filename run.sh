
nohup java -jar -Dspring.profiles.active=profile1 service-discovery-0.0.1-SNAPSHOT.jar > service-discovery.log &
nohup java -jar service-monitoring-0.0.1-SNAPSHOT.jar > service-monitoring.log & 
nohup java -jar api-gateway-0.0.1-SNAPSHOT.jar > api-gateway.log & 

nohup java -jar -Dspring.profiles.active=profile1 coin-service-0.0.1-SNAPSHOT.jar > coin-service1.log &
nohup java -jar -Dspring.profiles.active=profile2 coin-service-0.0.1-SNAPSHOT.jar > coin-service2.log &
nohup java -jar -Dspring.profiles.active=profile3 coin-service-0.0.1-SNAPSHOT.jar > coin-service3.log &

nohup java -jar -Dspring.profiles.active=profile1 user-service-0.0.1-SNAPSHOT.jar > user-service1.log &
nohup java -jar -Dspring.profiles.active=profile2 user-service-0.0.1-SNAPSHOT.jar > user-service2.log &
nohup java -jar -Dspring.profiles.active=profile3 user-service-0.0.1-SNAPSHOT.jar > user-service3.log &

nohup java -jar -Dspring.profiles.active=profile1 numismatic-service-0.0.1-SNAPSHOT.jar > numismatic-service1.log &
nohup java -jar -Dspring.profiles.active=profile2 numismatic-service-0.0.1-SNAPSHOT.jar > numismatic-service2.log &
nohup java -jar -Dspring.profiles.active=profile3 numismatic-service-0.0.1-SNAPSHOT.jar > numismatic-service3.log &
