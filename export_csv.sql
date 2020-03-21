select * from num_service_monitoring.monitoring_data
INTO OUTFILE '/var/lib/mysql-files/service_monitoring.csv'
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n';
