package it.univaq.disim.discovery.servicediscovery.dashboard.controller;

import it.univaq.disim.discovery.servicediscovery.dashboard.model.ServiceInstancesResource;
import it.univaq.disim.discovery.servicediscovery.registry.model.ServiceInstance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/dashboard")
public interface DashboardController {

    @GetMapping
    ResponseEntity<ServiceInstancesResource> getInstance();

}
