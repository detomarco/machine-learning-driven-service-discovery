package it.univaq.disim.discovery.common.property;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class DiscoveryAddressProperties {

    @NotNull
    @NotEmpty
    private List<String> serviceDiscovery;

    @NotNull
    private String serviceMonitoring;

    @NotNull
    @NotEmpty
    private List<String> mlEngine;

}
