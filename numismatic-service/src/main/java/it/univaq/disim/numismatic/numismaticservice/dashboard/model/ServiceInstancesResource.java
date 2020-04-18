package it.univaq.disim.numismatic.numismaticservice.dashboard.model;

import it.univaq.disim.discovery.common.domain.ServiceInstance;
import lombok.Data;

import java.util.List;

@Data
public class ServiceInstancesResource {

    private List<ServiceInstance> serviceInstances;

}
