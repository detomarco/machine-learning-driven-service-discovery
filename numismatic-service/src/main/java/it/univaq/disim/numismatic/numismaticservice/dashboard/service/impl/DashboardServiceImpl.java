package it.univaq.disim.numismatic.numismaticservice.dashboard.service.impl;

import it.univaq.disim.discovery.common.domain.ServiceInstance;
import it.univaq.disim.discovery.common.property.DiscoveryProperties;
import it.univaq.disim.discovery.common.utils.Utils;
import it.univaq.disim.numismatic.numismaticservice.dashboard.model.ServiceInstancesResource;
import it.univaq.disim.numismatic.numismaticservice.dashboard.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private DiscoveryProperties discoveryProperties;

    @Override
    public List<ServiceInstance> getInstance() {

        RestTemplate restTemplate = new RestTemplate();
        String serviceDiscoveryAddress = Utils.getRandomElement(discoveryProperties.getAddress().getServiceDiscovery());
        ServiceInstancesResource resource = restTemplate.getForObject(serviceDiscoveryAddress + "/dashboard", ServiceInstancesResource.class);

        return resource.getServiceInstances();
    }
}
