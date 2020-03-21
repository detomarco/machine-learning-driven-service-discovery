#!/bin/bash
mysql < export_csv.sql
cd /var/lib/mysql-files && zip -rm /home/msml/dataset.zip *.csv && (cd - || exit)
