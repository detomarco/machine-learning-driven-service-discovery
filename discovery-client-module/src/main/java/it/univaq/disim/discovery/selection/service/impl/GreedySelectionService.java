package it.univaq.disim.discovery.selection.service.impl;

import it.univaq.disim.discovery.common.domain.RankedServiceInstance;
import it.univaq.disim.discovery.common.domain.ServiceInstance;
import it.univaq.disim.discovery.selection.service.SelectionService;
import it.univaq.disim.discovery.monitoring.model.MonitoringData;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service("greedy")
public class GreedySelectionService implements SelectionService {

    @Override
    public ServiceInstance selectInstance(String serviceType, List<RankedServiceInstance> serviceInstances) {
        Optional<RankedServiceInstance> bestRanked = serviceInstances.stream().min(Comparator.comparing(RankedServiceInstance::getRanking));
        return bestRanked.get();
    }

    @Override
    public void postAction(MonitoringData monitoringData) {

    }
}
