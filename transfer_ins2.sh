#!/bin/bash

## INSTANCE TWO
scp -i ~/.ssh/ms_ml_gcp ./user-service/target/user-service-0.0.1-SNAPSHOT.jar msml@35.184.70.102:/home/msml
scp -i ~/.ssh/ms_ml_gcp ./stub-service/target/stub-service-0.0.1-SNAPSHOT.jar msml@35.184.70.102:/home/msml
scp -i ~/.ssh/ms_ml_gcp ./coin-service/target/coin-service-0.0.1-SNAPSHOT.jar msml@35.184.70.102:/home/msml

scp -i ~/.ssh/ms_ml_gcp ./config.yml msml@35.184.70.102:/home/msml
scp -i ~/.ssh/ms_ml_gcp ./run_ins2.sh msml@35.184.70.102:/home/msml
scp -i ~/.ssh/ms_ml_gcp ./run_client.sh msml@35.184.70.102:/home/msml
scp -i ~/.ssh/ms_ml_gcp ./run_ml.sh msml@35.184.70.102:/home/msml
scp -i ~/.ssh/ms_ml_gcp ./rerun_ml.sh msml@35.184.70.102:/home/msml
scp -i ~/.ssh/ms_ml_gcp ./rm.sh msml@35.184.70.102:/home/msml
scp -r -i ~/.ssh/ms_ml_gcp ./ml-engine msml@35.184.70.102:/home/msml
scp -r -i ~/.ssh/ms_ml_gcp ./ml-scripts msml@35.184.70.102:/home/msml
