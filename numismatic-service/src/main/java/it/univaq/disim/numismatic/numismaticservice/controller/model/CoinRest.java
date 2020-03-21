package it.univaq.disim.numismatic.numismaticservice.controller.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CoinRest extends BaseCoinRest {

    private Integer yearTo;
    private Double diameter;
    private Double thickness;
    private Double weight;
    private byte[] image;

}
