package it.univaq.disim.discovery.servicediscovery.dashboard.service;

import it.univaq.disim.discovery.servicediscovery.registry.model.ServiceInstance;

import java.util.List;

public interface DashboardService {

    List<ServiceInstance> getInstance();

}
