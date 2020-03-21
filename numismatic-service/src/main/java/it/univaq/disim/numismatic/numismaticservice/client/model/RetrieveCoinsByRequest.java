
package it.univaq.disim.numismatic.numismaticservice.client.model;

import lombok.Data;

@Data
public class RetrieveCoinsByRequest {

    private ExampleCoinType example;
    private CoinFieldsType fields;

}
