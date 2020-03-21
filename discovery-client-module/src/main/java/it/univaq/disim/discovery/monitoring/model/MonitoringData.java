package it.univaq.disim.discovery.monitoring.model;


import lombok.Data;

import java.util.Date;

@Data
public class MonitoringData {

    private String serviceType;
    private String serviceInstance;
    private Date timestamp;
    private Long responseTime;
    private Double meanResponseTime;
    private String clientContext;
    private String serviceContext;
    private RequestStatus status;

}
