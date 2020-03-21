package it.univaq.disim.discovery.monitoring.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"name", "url"})
public class RegistryServiceInstance {

    private String name;
    private String url;
    private String context;
    private Double meanResponseTime;

}
