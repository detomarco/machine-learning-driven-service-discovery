package it.univaq.disim.discovery.monitoring.repository;

import it.univaq.disim.discovery.monitoring.model.MonitoringData;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.xml.ws.ServiceMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class InMemoryRepository implements Repository {

    @Getter
    private List<MonitoringData> repository;

    @Getter @Setter
    private Double meanResponseTime = 0D;
    private Long callCounter = 0L;
    private Integer currentHour;

    @PostConstruct
    private void initRepository() {
        repository = new ArrayList<>();
    }

    @Override
    public void monitorData(MonitoringData monitoringData, Date startTime) {

        long responseTime = System.currentTimeMillis() - startTime.getTime();
        if (this.currentHour == null || !this.currentHour.equals(LocalDateTime.now().getHour())) {
            this.currentHour = LocalDateTime.now().getHour();
            this.callCounter = 0L;
            this.meanResponseTime = 0D;
            log.info("Current hour changed {}", this.currentHour);
        }
        this.callCounter++;
        this.meanResponseTime = ((this.meanResponseTime * (this.callCounter - 1)) + responseTime) / this.callCounter;
        this.repository.add(monitoringData);

    }

    @Override
    public List<MonitoringData> getMonitoredData() {
        return repository;
    }

    @Override
    public void clear() {
        this.repository.clear();
    }
}


