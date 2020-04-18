package it.univaq.disim.numismatic.userservice.controller;


import it.univaq.disim.numismatic.userservice.controller.model.LoginRequest;
import it.univaq.disim.numismatic.userservice.controller.model.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface UserController {

    @PostMapping("login")
    ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest parameters);

}
