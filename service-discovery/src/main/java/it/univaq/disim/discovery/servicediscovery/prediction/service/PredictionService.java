package it.univaq.disim.discovery.servicediscovery.prediction.service;

import it.univaq.disim.discovery.servicediscovery.prediction.engine.model.MLEngineResponse;
import it.univaq.disim.discovery.servicediscovery.registry.model.RankedServiceInstance;
import it.univaq.disim.discovery.servicediscovery.registry.model.ServiceInstance;

import java.util.List;

public interface PredictionService {

    List<RankedServiceInstance> predictValues(String serviceType, List<ServiceInstance> serviceInstances, String clientContext);

    <T> MLEngineResponse callMLEngine(String serviceType, String clientContext, T request);
}
