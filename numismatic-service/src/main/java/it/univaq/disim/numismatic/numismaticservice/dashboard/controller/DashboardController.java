package it.univaq.disim.numismatic.numismaticservice.dashboard.controller;

import it.univaq.disim.numismatic.numismaticservice.dashboard.model.ServiceInstancesResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/dashboard")
public interface DashboardController {

    @GetMapping
    ResponseEntity<ServiceInstancesResource> getInstance();

}
