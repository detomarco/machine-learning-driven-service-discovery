package it.univaq.disim.numismatic.numismaticservice.business;

import java.util.List;

public interface CountryService {

    /**
     * Retrieve all the countries
     *
     * @return countries retrieved
     */
    List<String> retrieveCountries();

    /**
     * Retrieve the countries for which a user has at least one coin
     *
     * @param username user
     *
     * @return countries retrieved
     */
    List<String> retrieveUserCountries(String username);

}
