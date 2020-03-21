
package it.univaq.disim.numismatic.coinservice.controller.model;

import lombok.Data;

@Data
public class CoinType {

    private Double value;
    private String type;
    private String country;
    private String series;
    private Integer yearFrom;
    private Integer yearTo;
    private Double diameter;
    private Double thickness;
    private Double weight;
    private byte[] image;

}
