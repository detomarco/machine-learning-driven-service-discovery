
package it.univaq.disim.numismatic.coinservice.controller.model;

import lombok.Data;

import java.util.List;

@Data
public class CoinsListResponse {

    protected List<CoinType> coins;

}
