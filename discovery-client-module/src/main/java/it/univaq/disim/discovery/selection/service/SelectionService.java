package it.univaq.disim.discovery.selection.service;

import it.univaq.disim.discovery.common.domain.RankedServiceInstance;
import it.univaq.disim.discovery.common.domain.ServiceInstance;
import it.univaq.disim.discovery.monitoring.model.MonitoringData;

import java.util.List;

public interface SelectionService {

    ServiceInstance selectInstance(String serviceType, List<RankedServiceInstance> serviceList);

    void postAction(MonitoringData monitoringData);
}
