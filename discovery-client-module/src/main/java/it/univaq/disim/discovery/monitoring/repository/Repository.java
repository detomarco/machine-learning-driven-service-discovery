package it.univaq.disim.discovery.monitoring.repository;

import it.univaq.disim.discovery.monitoring.model.MonitoringData;

import java.util.Date;
import java.util.List;

public interface Repository {

    void monitorData(MonitoringData monitoringData, Date startTime);

    List<MonitoringData> getMonitoredData();

    Double getMeanResponseTime();

    void clear();
}
