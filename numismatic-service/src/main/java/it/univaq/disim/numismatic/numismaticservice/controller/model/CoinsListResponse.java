package it.univaq.disim.numismatic.numismaticservice.controller.model;

import lombok.Data;

import java.util.List;

@Data
public class CoinsListResponse {

    private List<CoinRest> coins;

}
