package it.univaq.disim.numismatic.numismaticservice.controller;

import it.univaq.disim.numismatic.numismaticservice.controller.model.UsersResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping
public interface UserController {

    /**
     * Retrieve nearby users. If latitude or longitude are null, the user's city is taken as centre
     *
     * @param radius radius
     *
     * @return nearby users
     */
    @GetMapping(value = "/v1/users/nearby", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<UsersResponse> nearby(@RequestParam Double radius);

}
