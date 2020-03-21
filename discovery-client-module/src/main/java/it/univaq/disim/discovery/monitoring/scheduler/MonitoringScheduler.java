package it.univaq.disim.discovery.monitoring.scheduler;

import it.univaq.disim.discovery.common.property.DiscoveryProperties;
import it.univaq.disim.discovery.monitoring.model.MonitoringServiceInstance;
import it.univaq.disim.discovery.monitoring.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

@Service
public class MonitoringScheduler {

    @Autowired
    private DiscoveryProperties discoveryProperties;

    @Autowired
    private Repository repository;

    @Scheduled(fixedDelayString = "${discovery.scheduler.cron.monitoring}")
    public void runMonitoring() {
        if (!CollectionUtils.isEmpty(repository.getMonitoredData())) {
            MonitoringServiceInstance serviceInstance = new MonitoringServiceInstance();
            serviceInstance.setRequests(repository.getMonitoredData());

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForLocation(discoveryProperties.getAddress().getServiceMonitoring(), serviceInstance);
            repository.clear();
        }
    }

}
