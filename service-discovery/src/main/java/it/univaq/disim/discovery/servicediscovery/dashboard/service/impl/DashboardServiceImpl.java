package it.univaq.disim.discovery.servicediscovery.dashboard.service.impl;

import it.univaq.disim.discovery.servicediscovery.dashboard.service.DashboardService;
import it.univaq.disim.discovery.servicediscovery.registry.model.ServiceInstance;
import it.univaq.disim.discovery.servicediscovery.registry.repository.RegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private RegistryRepository registryRepository;

    @Override
    public List<ServiceInstance> getInstance() {
        return registryRepository.findAll();
    }
}
