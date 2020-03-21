package it.univaq.disim.discovery.servicediscovery.prediction.engine;

import it.univaq.disim.discovery.servicediscovery.registry.model.RankedServiceInstance;
import it.univaq.disim.discovery.servicediscovery.registry.model.ServiceInstance;

import java.util.List;

public interface PredictionEngine {

    /**
     * fill service instance with ranking value given by machine learning algorithm
     * @param serviceType service type
     * @param serviceInstances service instance
     * @param clientContext client context
     * @return ranked service instances
     */
    List<RankedServiceInstance> predictValues(String serviceType, List<ServiceInstance> serviceInstances, String clientContext);

}
