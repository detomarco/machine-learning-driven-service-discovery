
-- Total average
select avg(response_time) from monitoring_data where id > 600000;

-- Avarage per hour
select day(timestamp), hour(timestamp), avg(response_time) from monitoring_data where id > 600000 group by hour(timestamp);

-- Avarage per hour and service type
select hour(timestamp), service_type, avg(response_time) from monitoring_data where id > 600000 group by service_type, hour(timestamp);
