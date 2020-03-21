package it.univaq.disim.numismatic.coinservice.controller;


import it.univaq.disim.numismatic.coinservice.controller.model.CoinsListResponse;
import it.univaq.disim.numismatic.coinservice.controller.model.RetrieveCoinsByRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CoinController {

    @PostMapping("retrieveBy")
    CoinsListResponse retrieveCoinsBy(@RequestBody RetrieveCoinsByRequest parameters);

}
