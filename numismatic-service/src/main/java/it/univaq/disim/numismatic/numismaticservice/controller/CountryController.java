package it.univaq.disim.numismatic.numismaticservice.controller;

import it.univaq.disim.numismatic.numismaticservice.controller.model.CountryListResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@RequestMapping
public interface CountryController {

    /**
     * Retrieve a list of countries
     *
     * @param username username. if null retrieve all countries, otherwise retrieve the country in which the user has at least one coin
     *
     * @return countries' list
     */
    @GetMapping(value = {"/v1/countries", "/v1/countries/{username}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<CountryListResponse> retrieveCountries(@PathVariable(required = false) Optional<String> username);

}
