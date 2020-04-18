package it.univaq.disim.discovery.servicediscovery.dashboard.controller.impl;

import it.univaq.disim.discovery.servicediscovery.dashboard.controller.DashboardController;
import it.univaq.disim.discovery.servicediscovery.dashboard.model.ServiceInstancesResource;
import it.univaq.disim.discovery.servicediscovery.dashboard.service.DashboardService;
import it.univaq.disim.discovery.servicediscovery.registry.model.ServiceInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class DashboardControllerImpl implements DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @Override
    public ResponseEntity<ServiceInstancesResource> getInstance() {
        ServiceInstancesResource resource = new ServiceInstancesResource();
        resource.setServiceInstances(dashboardService.getInstance());
        return ok(resource);
    }
}
