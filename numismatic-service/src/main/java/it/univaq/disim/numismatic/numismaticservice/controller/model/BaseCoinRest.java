package it.univaq.disim.numismatic.numismaticservice.controller.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BaseCoinRest {

    private Long id;

    @NotNull
    @NotBlank
    private String type;

    @NotNull
    @NotBlank
    private String country;

    @NotNull
    private Double value;

    @NotNull
    @NotBlank
    private String series;

    @NotNull
    private Integer yearFrom;

    public void setType(String type) {
        if (type != null) this.type = type.trim();
    }

    public void setCountry(String country) {
        if (country != null) this.country = country.trim();
    }

    public void setSeries(String series) {
        if (series != null) this.series = series.trim();
    }

}
