package it.univaq.disim.numismatic.numismaticservice.controller.impl;

import it.univaq.disim.numismatic.numismaticservice.business.CountryService;
import it.univaq.disim.numismatic.numismaticservice.controller.CountryController;
import it.univaq.disim.numismatic.numismaticservice.controller.model.CountryListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class    CountryControllerImpl implements CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryControllerImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public ResponseEntity<CountryListResponse> retrieveCountries(Optional<String> username) {

        CountryListResponse response = new CountryListResponse();
        if (!username.isPresent()) { // retrieve all countries
            response.setCountries(countryService.retrieveCountries());
        } else { // retrieve the country in which the user has at least one coin
            response.setCountries(countryService.retrieveUserCountries(username.get()));

        }

        return ResponseEntity.ok(response);
    }

}
