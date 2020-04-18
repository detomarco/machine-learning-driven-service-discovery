package it.univaq.disim.numismatic.userservice.controller;


import it.univaq.disim.numismatic.userservice.controller.model.LoginRequest;
import it.univaq.disim.numismatic.userservice.controller.model.LoginResponse;
import it.univaq.disim.numismatic.userservice.controller.model.UserType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserControllerImpl implements UserController {

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest parameters) {
        LoginResponse response = new LoginResponse();
        UserType userType = new UserType();
        userType.setEnabled(true);
        response.setUser(userType);
        return ResponseEntity.ok(response);
    }

}
