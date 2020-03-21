package it.univaq.disim.discovery.servicediscovery.registry.service.impl;

import it.univaq.disim.discovery.servicediscovery.prediction.service.PredictionService;
import it.univaq.disim.discovery.servicediscovery.registry.model.RankedServiceInstance;
import it.univaq.disim.discovery.servicediscovery.registry.model.ServiceInstance;
import it.univaq.disim.discovery.servicediscovery.registry.repository.RegistryRepository;
import it.univaq.disim.discovery.servicediscovery.registry.service.RegistryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
public class RegistryServiceImpl implements RegistryService {

    @Autowired
    private RegistryRepository registryRepository;

    @Autowired
    private PredictionService predictionService;

    public List<RankedServiceInstance> getServiceInstance(String serviceType, String clientContext) {
        List<ServiceInstance> serviceInstances = registryRepository.find(serviceType);
        if (CollectionUtils.isEmpty(serviceInstances)) {
            return null;
        } else {
            // fill service instances with ranking value
            return predictionService.predictValues(serviceType, serviceInstances, clientContext);
        }
    }

    public void removeServiceInstance(ServiceInstance serviceInstance) {
        registryRepository.remove(serviceInstance);
    }

    public void registerServiceInstance(ServiceInstance serviceInstance) {
        registryRepository.register(serviceInstance);
    }

}
