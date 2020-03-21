package it.univaq.disim.discovery.servicediscovery.registry.repository;

import it.univaq.disim.discovery.servicediscovery.registry.model.ServiceInstance;

import java.util.List;

public interface RegistryRepository {

    List<ServiceInstance> find(String serviceType);

    List<ServiceInstance> findAll();

    void remove(ServiceInstance serviceInstance);

    void register(ServiceInstance serviceInstance);

}
