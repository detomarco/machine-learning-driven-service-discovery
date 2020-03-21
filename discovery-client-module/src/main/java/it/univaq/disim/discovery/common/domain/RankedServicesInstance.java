package it.univaq.disim.discovery.common.domain;

import lombok.Data;

import java.util.List;

@Data
public class RankedServicesInstance {
    private List<RankedServiceInstance> instances;
}
