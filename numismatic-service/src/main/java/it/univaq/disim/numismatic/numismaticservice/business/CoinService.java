package it.univaq.disim.numismatic.numismaticservice.business;

import it.univaq.disim.numismatic.numismaticservice.domain.Coin;
import it.univaq.disim.numismatic.numismaticservice.domain.CoinField;

import java.util.List;

public interface CoinService {

    /**
     * Retrieve coins that match with an example.
     * Moreover, the coins in the user collection will have an identifier
     *
     * @param username username of the user
     * @param example  coin example
     *
     * @return coin founded
     */
    List<Coin> retrieveCoinsByExample(String username, Coin example, List<CoinField> fields);

    /**
     * Retrieve all the coins of a user that match with example
     *
     * @param username username
     * @param example  coin example
     *
     * @return coin founded
     */
    List<Coin> retrieveUserCoins(String username, Coin example, List<CoinField> fields);


}
