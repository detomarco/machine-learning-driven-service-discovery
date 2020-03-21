package it.univaq.disim.discovery.common.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"name", "url"})
public class ServiceInstance {

    private String name;
    private String url;
    private String context;

}
