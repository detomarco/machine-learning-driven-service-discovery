
package it.univaq.disim.numismatic.numismaticservice.client.model;

import lombok.Data;

@Data
public class ExampleCoinType {

    private Double value;
    private String type;
    private String country;
    private String series;
    private Integer yearFrom;
    private Integer yearTo;
    private Double diameter;
    private Double thickness;
    private Double weight;

}
