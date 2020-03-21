package it.univaq.disim.discovery.servicediscovery.registry.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RankedServicesInstance {
    private List<RankedServiceInstance> instances;
}
