package it.univaq.disim.discovery.selection.service.impl;

import it.univaq.disim.discovery.common.domain.RankedServiceInstance;
import it.univaq.disim.discovery.common.domain.ServiceInstance;
import it.univaq.disim.discovery.common.utils.Utils;
import it.univaq.disim.discovery.monitoring.model.MonitoringData;
import it.univaq.disim.discovery.selection.service.SelectionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("random")
public class RandomSelectionService implements SelectionService {

    @Override
    public ServiceInstance selectInstance(String serviceType, List<RankedServiceInstance> serviceInstances) {
        return Utils.getRandomElement(serviceInstances);
    }

    @Override
    public void postAction(MonitoringData monitoringData) {

    }
}
