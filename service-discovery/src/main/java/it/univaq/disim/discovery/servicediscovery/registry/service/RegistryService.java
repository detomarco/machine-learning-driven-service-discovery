package it.univaq.disim.discovery.servicediscovery.registry.service;

import it.univaq.disim.discovery.servicediscovery.registry.model.RankedServiceInstance;
import it.univaq.disim.discovery.servicediscovery.registry.model.ServiceInstance;

import java.util.List;

public interface RegistryService {

    List<RankedServiceInstance> getServiceInstance(String serviceType, String clientContext);

    void removeServiceInstance(ServiceInstance serviceInstance);

    void registerServiceInstance(ServiceInstance serviceInstance);

}
