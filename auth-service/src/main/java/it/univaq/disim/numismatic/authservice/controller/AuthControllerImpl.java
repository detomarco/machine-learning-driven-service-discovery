package it.univaq.disim.numismatic.authservice.controller;

import it.univaq.disim.numismatic.authservice.controller.model.LoginRequest;
import it.univaq.disim.numismatic.authservice.controller.model.LoginResponse;
import it.univaq.disim.numismatic.authservice.controller.model.UserType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthControllerImpl implements AuthController {

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest parameters) {
        LoginResponse response = new LoginResponse();
        UserType userType = new UserType();
        userType.setEnabled(true);
        response.setUser(userType);
        return ResponseEntity.ok(response);
    }

}
