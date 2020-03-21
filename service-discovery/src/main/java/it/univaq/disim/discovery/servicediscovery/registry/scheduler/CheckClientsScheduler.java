package it.univaq.disim.discovery.servicediscovery.registry.scheduler;

import it.univaq.disim.discovery.servicediscovery.prediction.service.DiscoveryRestTemplate;
import it.univaq.disim.discovery.servicediscovery.registry.model.ServiceInstance;
import it.univaq.disim.discovery.servicediscovery.registry.repository.RegistryRepository;
import it.univaq.disim.discovery.servicediscovery.registry.service.RegistryService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CheckClientsScheduler {

    @Autowired
    private RegistryService registryService;

    @Autowired
    private RegistryRepository registryRepository;

    @Autowired
    private DiscoveryRestTemplate discoveryRestTemplate;

    @Scheduled(fixedDelayString = "${discovery.scheduler.cron.monitoring}")
    public void healthCheck() {
        List<ServiceInstance> services = registryRepository.findAll();
        if (services != null) {
            services.forEach(instance -> {
                String url = instance.getUrl() + "/actuator/health";
                try {
                    HealthStatus response = discoveryRestTemplate.get(url, HealthStatus.class);
                    if (response.getStatus() != HealthEnum.UP) {
                        registryService.removeServiceInstance(instance);
                    }
                } catch (Exception e) {
                    registryService.removeServiceInstance(instance);
                    log.error(e.getMessage(), e);
                }
            });
        }
    }


    @Data
    private static class HealthStatus {
        private HealthEnum status;
    }

    private static enum HealthEnum {
        UP
    }

}
