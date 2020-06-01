# A Machine Learning-driven approach to ServiceDiscovery for Microservice architectures
=========================================

## Installation :

### Prerequisites
* Java 8
* Maven 3
* Python 3.7
    * Modules:
        * Flask@1.1.1
        * joblib@0.14.1
        * Keras@2.3.1
        * numpy@1.19.1
        * pandas@1.0.1
        * pyzip@0.2.0
        * scikit-learn@0.22.2
        * tensorflow@1.15.0
* Mysql:
    * Databases:
        * num_service_monitoring.
            - Username: num_service_monitoring
            - Password: num_service_monitoring
            - Port 3306
        * numismatic_service.
            - Username: numismatic_service
            - Password: numismatic_service
            - Port 3306
        * user_service.
            - Username: user_service
            - Password: user_service
            - Port 3306
        * coin_service.
            - Username: coin_service
            - Password: coin_service
            - Port 3306

### Build microservices

```bash
mvn -f ./discovery-client-module/pom.xml clean install
mvn -f ./api-gateway/pom.xml clean install
mvn -f ./numismatic-service/pom.xml clean install
mvn -f ./coin-service/pom.xml clean install
mvn -f ./service-discovery/pom.xml clean install
mvn -f ./service-monitoring/pom.xml clean install
mvn -f ./user-service/pom.xml clean install
mvn -f ./stub-service/pom.xml clean install
mvn -f ./auth-service/pom.xml clean install
```

### App configuration

In *config.yml* file it's possible to configure the application (service discovery location, machine learning strategies and so on). 
You will find more details into *config.yml* file.

## Start application

### Run microservices
For each microservice, go to the target directory and execute the jar file.

For instance, for numismatic service:
```bash
cd numismatic-service/target
java -jar -Dspring.profiles.active=profile1 numismatic-service-0.0.1-SNAPSHOT.jar
```

You can specify different profile (from 1 to 5) in order to run multiple instances of the same microservice that have different response delays over time.

### Run machine learning engine
```bash
cd ml-engine
python3 ./app.py
```

### Run client
```bash
python3 client.py > client.log &
```