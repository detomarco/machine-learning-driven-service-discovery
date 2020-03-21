package it.univaq.disim.numismatic.coinservice.business.repository;


import it.univaq.disim.numismatic.coinservice.business.domain.Coin;

import java.util.List;

public interface CoinRepository extends BaseRepository<Coin> {
    List<Coin> retrieveCoinsByExample(Coin example);

}
