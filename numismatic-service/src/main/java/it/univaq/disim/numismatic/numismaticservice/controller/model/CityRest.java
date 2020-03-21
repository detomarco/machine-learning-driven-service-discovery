package it.univaq.disim.numismatic.numismaticservice.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CityRest {

    @NotNull
    @NotBlank
    private String name;
    private Double lat;
    private Double lng;

    public void setName(String name) {
        if (name != null) this.name = name.trim();
    }

}
