package it.univaq.disim.discovery.common.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RankedServiceInstance extends ServiceInstance {

    private Double ranking;

}
