package it.univaq.disim.discovery.servicediscovery.registry.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FullServiceInstance {

    @NotNull
    private String name;
    @NotNull
    private String url;
    @NotNull
    private String clientContext;
    @NotNull
    private String serviceContext;
    @NotNull
    private Double meanResponseTime;

    private Long timestamp;
}
