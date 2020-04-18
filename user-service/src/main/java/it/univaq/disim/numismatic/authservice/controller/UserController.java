package it.univaq.disim.numismatic.authservice.controller;


import it.univaq.disim.numismatic.authservice.controller.model.LoginRequest;
import it.univaq.disim.numismatic.authservice.controller.model.LoginResponse;
import it.univaq.disim.numismatic.authservice.controller.model.NearbyUsersByUserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public interface UserController {

    @GetMapping("/nearbyuser")
    ResponseEntity<NearbyUsersByUserResponse> getNearbyUsersByUser(
            @Valid @NotEmpty @NotNull @RequestParam("username") String username,
            @Valid @NotNull @RequestParam("radius") Double radius);

    @PostMapping("login")
    ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest parameters);

}
