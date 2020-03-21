package it.univaq.disim.numismatic.numismaticservice.controller.impl;

import it.univaq.disim.numismatic.numismaticservice.business.CoinService;
import it.univaq.disim.numismatic.numismaticservice.controller.CoinController;
import it.univaq.disim.numismatic.numismaticservice.controller.mapper.RestMapper;
import it.univaq.disim.numismatic.numismaticservice.controller.model.CoinsListResponse;
import it.univaq.disim.numismatic.numismaticservice.controller.model.RetrieveCoinRequest;
import it.univaq.disim.numismatic.numismaticservice.domain.Coin;
import it.univaq.disim.numismatic.numismaticservice.domain.CoinField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CoinControllerImpl implements CoinController {

    private final CoinService coinService;

    @Autowired
    public CoinControllerImpl(CoinService coinService) {
        this.coinService = coinService;
    }

    @Override
    public ResponseEntity<CoinsListResponse> retrieveCoins(RetrieveCoinRequest request) {

        Coin coin = RestMapper.toModel(request);
        List<CoinField> fields = RestMapper.toCoinFieldModel(request.getFields());
        List<Coin> coinList;

        if (request.getUsername() != null) { // retrieve user's coins
            coinList = coinService.retrieveUserCoins(request.getUsername(), coin, fields);
        } else { // retrieve all coins
            // retrieve authentication
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            coinList = coinService.retrieveCoinsByExample(authentication.getName(), coin, fields);
        }

        CoinsListResponse coinsListResponse = new CoinsListResponse();
        coinsListResponse.setCoins(RestMapper.toCoinRestList(coinList));

        return ResponseEntity.ok(coinsListResponse);
    }

}
