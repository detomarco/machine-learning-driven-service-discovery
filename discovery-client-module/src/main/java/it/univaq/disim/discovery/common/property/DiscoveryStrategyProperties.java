package it.univaq.disim.discovery.common.property;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DiscoveryStrategyProperties {

    @NotNull
    private String selection;
    @NotNull
    private String prediction;

}
