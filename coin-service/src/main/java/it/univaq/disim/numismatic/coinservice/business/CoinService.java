package it.univaq.disim.numismatic.coinservice.business;


import it.univaq.disim.numismatic.coinservice.business.domain.Coin;

import java.util.List;

public interface CoinService {

    List<Coin> retrieveCoinsByExample(Coin example);

}
