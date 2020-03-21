package it.univaq.disim.discovery.servicediscovery.registry.controller;

import it.univaq.disim.discovery.servicediscovery.registry.model.RankedServiceInstance;
import it.univaq.disim.discovery.servicediscovery.registry.model.RankedServicesInstance;
import it.univaq.disim.discovery.servicediscovery.registry.model.ServiceInstance;
import it.univaq.disim.discovery.servicediscovery.registry.service.RegistryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
public class RegistryController {

    @Autowired
    private RegistryService registryService;

    @GetMapping("/{clientContext}/{service}")
    public ResponseEntity<RankedServicesInstance> getServiceInstance(@PathVariable("service") String serviceType, @PathVariable("clientContext") String clientContext) {
        List<RankedServiceInstance> serviceInstance = registryService.getServiceInstance(serviceType, clientContext);
        if (serviceInstance == null) {
            throw new RuntimeException("No instance found for service " + serviceType);
        }
        log.info("Retrieve instance: {}", serviceInstance);
        return ResponseEntity.ok(new RankedServicesInstance(serviceInstance));
    }

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody ServiceInstance serviceInstance) {
        registryService.registerServiceInstance(serviceInstance);
        return ResponseEntity.ok().build();
    }

}
