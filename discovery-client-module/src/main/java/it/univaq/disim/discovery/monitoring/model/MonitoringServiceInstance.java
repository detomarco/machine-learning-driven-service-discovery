package it.univaq.disim.discovery.monitoring.model;

import lombok.Data;

import java.util.List;

@Data
public class MonitoringServiceInstance{

    private List<MonitoringData> requests;

}
