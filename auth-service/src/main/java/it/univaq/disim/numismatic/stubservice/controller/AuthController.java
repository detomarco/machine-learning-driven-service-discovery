package it.univaq.disim.numismatic.stubservice.controller;


import it.univaq.disim.numismatic.stubservice.controller.model.LoginRequest;
import it.univaq.disim.numismatic.stubservice.controller.model.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface AuthController {

    @PostMapping("login")
    ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest parameters);

}
