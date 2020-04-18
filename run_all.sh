#!/bin/bash

sh run_ml.sh
sh run.sh

echo "waiting for server"
sleep 5m
sh run_client.sh
echo "done"
