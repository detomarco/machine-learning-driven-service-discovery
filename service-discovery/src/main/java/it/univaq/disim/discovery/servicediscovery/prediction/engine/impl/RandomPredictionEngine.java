package it.univaq.disim.discovery.servicediscovery.prediction.engine.impl;

import it.univaq.disim.discovery.servicediscovery.prediction.engine.PredictionEngine;
import it.univaq.disim.discovery.servicediscovery.registry.model.RankedServiceInstance;
import it.univaq.disim.discovery.servicediscovery.registry.model.ServiceInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("random")
public class RandomPredictionEngine implements PredictionEngine {

    @Override
    public List<RankedServiceInstance> predictValues(String serviceType, List<ServiceInstance> serviceInstances, String clientContext) {
        // assign 1 as ranking value
        return serviceInstances.stream().map(serviceInstance -> {
            RankedServiceInstance rankedServiceInstance = new RankedServiceInstance();
            rankedServiceInstance.setName(serviceInstance.getName());
            rankedServiceInstance.setContext(serviceInstance.getContext());
            rankedServiceInstance.setUrl(serviceInstance.getUrl());
            rankedServiceInstance.setRanking(Math.random());
            return rankedServiceInstance;
        }).collect(Collectors.toList());

    }

}
