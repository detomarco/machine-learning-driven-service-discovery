package it.univaq.disim.discovery.registry.scheduler;

import it.univaq.disim.discovery.common.DiscoveryRestTemplate;
import it.univaq.disim.discovery.common.property.DiscoveryProperties;
import it.univaq.disim.discovery.monitoring.model.RegistryServiceInstance;
import it.univaq.disim.discovery.monitoring.repository.Repository;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@ConditionalOnProperty(value = "discovery.register", matchIfMissing = true)
@Service
public class RegistryScheduler {

    @Autowired
    private DiscoveryProperties discoveryProperties;

    @Autowired
    private Repository repository;

    @Value("${server.port}")
    private String serverPort;

    @Value("${spring.application.name}")
    private String applicationName;

    @Scheduled(fixedDelayString = "${discovery.scheduler.cron.registry}", initialDelay = 0)
    public void runRegistry() throws UnknownHostException {
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        RegistryServiceInstance serviceInstance = new RegistryServiceInstance();
        serviceInstance.setUrl("http://" + hostAddress + ":" + serverPort);
        serviceInstance.setName(applicationName);
        serviceInstance.setContext(discoveryProperties.getContext());
        serviceInstance.setMeanResponseTime(repository.getMeanResponseTime());
        log.info("Service Registration {}", serviceInstance);
        // save at each available service discovery
        discoveryProperties.getAddress().getServiceDiscovery()
                .forEach(serviceDiscovery -> {
                    RestTemplate restTemplate = new RestTemplate();
                    restTemplate.postForLocation(serviceDiscovery, serviceInstance);
                });
    }
}
