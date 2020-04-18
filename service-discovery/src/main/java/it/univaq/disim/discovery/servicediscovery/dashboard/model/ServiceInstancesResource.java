package it.univaq.disim.discovery.servicediscovery.dashboard.model;

import it.univaq.disim.discovery.servicediscovery.registry.model.ServiceInstance;
import lombok.Data;

import java.util.List;

@Data
public class ServiceInstancesResource {

    private List<ServiceInstance> serviceInstances;

}
