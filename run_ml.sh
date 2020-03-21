#!/bin/bash
cd ml-engine || exit
nohup python3 ./app.py > ../ml-engine.log &
