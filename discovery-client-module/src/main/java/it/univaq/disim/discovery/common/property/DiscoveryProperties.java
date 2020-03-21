package it.univaq.disim.discovery.common.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
@Valid
@Component
@ConfigurationProperties(prefix = "discovery")
public class DiscoveryProperties {

    @NotNull
    private String context;

    @Valid
    @NotNull
    @NestedConfigurationProperty
    private DiscoveryAddressProperties address;

    @Valid
    @NotNull
    @NestedConfigurationProperty
    private DiscoveryStrategyProperties strategy;

    private Map<Integer, Double> delay;
    private Boolean register;

}
