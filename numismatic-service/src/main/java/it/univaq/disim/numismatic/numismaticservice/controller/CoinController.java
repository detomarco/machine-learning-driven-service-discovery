package it.univaq.disim.numismatic.numismaticservice.controller;

import it.univaq.disim.numismatic.numismaticservice.controller.model.CoinsListResponse;
import it.univaq.disim.numismatic.numismaticservice.controller.model.RetrieveCoinRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@RequestMapping
public interface CoinController {

    /**
     * Retrieve coins by example.
     * If in the example there is a username, retrieve the user's coins
     * If in the example there is not a username, retrieve all the coin that match with the example and set the id if the logged user has the coin
     *
     * @param request coin query
     *
     * @return coins list
     */
    @GetMapping(value = "/v1/coins", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<CoinsListResponse> retrieveCoins(@Valid @ModelAttribute RetrieveCoinRequest request);

}
