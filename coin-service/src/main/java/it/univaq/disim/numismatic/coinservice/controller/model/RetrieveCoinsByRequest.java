
package it.univaq.disim.numismatic.coinservice.controller.model;

import lombok.Data;

@Data
public class RetrieveCoinsByRequest {

    private ExampleCoinType example;
    private CoinFieldsType fields;

}
