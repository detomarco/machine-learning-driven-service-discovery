package it.univaq.disim.discovery.servicemonitoring.monitoring.controller;

import it.univaq.disim.discovery.servicemonitoring.monitoring.model.MonitoringServiceInstance;
import it.univaq.disim.discovery.servicemonitoring.monitoring.service.MonitoringService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
public class MonitoringController {

    @Autowired
    private MonitoringService monitoringService;

    @PostMapping
    public ResponseEntity<?> monitoring(@Valid @RequestBody MonitoringServiceInstance serviceInstance) {
        monitoringService.requestMonitoring(serviceInstance);
        return ResponseEntity.ok().build();
    }
}
