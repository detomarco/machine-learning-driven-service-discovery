package it.univaq.disim.numismatic.coinservice.controller;

import it.univaq.disim.numismatic.coinservice.business.CoinService;
import it.univaq.disim.numismatic.coinservice.business.domain.Coin;
import it.univaq.disim.numismatic.coinservice.controller.model.CoinFieldType;
import it.univaq.disim.numismatic.coinservice.controller.model.CoinsListResponse;
import it.univaq.disim.numismatic.coinservice.controller.model.RetrieveCoinsByRequest;
import it.univaq.disim.numismatic.coinservice.mapper.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class CoinControllerImpl implements CoinController {

    private final CoinService coinService;

    @Autowired
    public CoinControllerImpl(CoinService coinService) {
        this.coinService = coinService;
    }

    @Override
    public CoinsListResponse retrieveCoinsBy(RetrieveCoinsByRequest parameters) {

        Coin example = Mapper.toModel(parameters.getExample());

        List<CoinFieldType> fields = null;
        if (parameters.getFields() != null &&
                parameters.getFields().getField() != null &&
                !parameters.getFields().getField().isEmpty()) {

            fields = parameters.getFields().getField();
        }

        // retrieve all coins
        List<Coin> coinList = coinService.retrieveCoinsByExample(example);

        CoinsListResponse res = new CoinsListResponse();
        res.setCoins(Mapper.toTypeByFields(coinList, fields));

        return res;
    }

}
