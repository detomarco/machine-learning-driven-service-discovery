package it.univaq.disim.discovery.servicemonitoring.monitoring.service;

import it.univaq.disim.discovery.servicemonitoring.monitoring.model.MonitoringServiceInstance;
import it.univaq.disim.discovery.servicemonitoring.monitoring.repository.MonitoringDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MonitoringService {

    @Value("${discovery.strategy.prediction}")
    private String predictionStrategy;

    @Value("${discovery.strategy.selection}")
    private String selectionStrategy;

    @Autowired
    private MonitoringDataRepository monitoringDataRepository;

    public void requestMonitoring(MonitoringServiceInstance monitoringServiceInstance) {
        monitoringServiceInstance.getRequests().forEach(requestMonitoring -> {
            requestMonitoring.setPredictionStrategy(predictionStrategy);
            requestMonitoring.setSelectionStrategy(selectionStrategy);
        });
        monitoringDataRepository.saveAll(monitoringServiceInstance.getRequests());
    }

}
