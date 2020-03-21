package it.univaq.disim.discovery.servicediscovery.registry.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(of = {"name", "url"})
public class ServiceInstance {
    @NotNull
    private String name;
    @NotNull
    private String url;
    @NotNull
    private String context;
    @NotNull
    private Double meanResponseTime;
}
