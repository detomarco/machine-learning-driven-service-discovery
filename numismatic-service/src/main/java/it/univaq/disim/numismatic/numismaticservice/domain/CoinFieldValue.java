package it.univaq.disim.numismatic.numismaticservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoinFieldValue {

    private Object value;
    private Long number;

}
