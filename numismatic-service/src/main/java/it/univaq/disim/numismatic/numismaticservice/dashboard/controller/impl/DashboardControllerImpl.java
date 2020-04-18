package it.univaq.disim.numismatic.numismaticservice.dashboard.controller.impl;

import it.univaq.disim.numismatic.numismaticservice.dashboard.controller.DashboardController;
import it.univaq.disim.numismatic.numismaticservice.dashboard.model.ServiceInstancesResource;
import it.univaq.disim.numismatic.numismaticservice.dashboard.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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
