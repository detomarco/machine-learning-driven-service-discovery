package it.univaq.disim.discovery.servicemonitoring.monitoring.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class MonitoringServiceInstance {

    @Valid
    @NotNull
    private List<MonitoringData> requests;

}
