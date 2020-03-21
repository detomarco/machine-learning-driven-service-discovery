package it.univaq.disim.discovery.servicediscovery.registry.model;

import lombok.Data;

@Data
public class RankedServiceInstance {
    private String url;
    private String name;
    private String context;
    private Double ranking;
}
