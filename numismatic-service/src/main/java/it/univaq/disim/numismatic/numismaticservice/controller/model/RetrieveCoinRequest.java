package it.univaq.disim.numismatic.numismaticservice.controller.model;

import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
public class RetrieveCoinRequest {

    @Nullable
    private String username;
    @Nullable
    private String type;
    @Nullable
    private String country;
    @Nullable
    private Double value;
    @Nullable
    private String series;
    @Nullable
    private Integer yearFrom;
    @Nullable
    private Integer yearTo;
    @Nullable
    private Double diameter;
    @Nullable
    private Double thickness;
    @Nullable
    private Double weight;
    @Nullable
    private List<String> fields;

}
