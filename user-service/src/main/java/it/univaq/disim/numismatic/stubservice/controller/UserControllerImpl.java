package it.univaq.disim.numismatic.stubservice.controller;

import it.univaq.disim.numismatic.stubservice.business.UserService;
import it.univaq.disim.numismatic.stubservice.business.domain.User;
import it.univaq.disim.numismatic.stubservice.controller.model.LoginRequest;
import it.univaq.disim.numismatic.stubservice.controller.model.LoginResponse;
import it.univaq.disim.numismatic.stubservice.controller.model.NearbyUsersByUserResponse;
import it.univaq.disim.numismatic.stubservice.mapper.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Autowired
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<NearbyUsersByUserResponse> getNearbyUsersByUser(String username, Double radius) {

        List<User> nearbyUsers = userService.nearbyUsersByUser(username, radius);
        NearbyUsersByUserResponse response = new NearbyUsersByUserResponse();
        response.setUsers(Mapper.toTypeUserList(nearbyUsers));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest parameters) {
        User user = userService.login(parameters.getUsername(), parameters.getPassword());
        LoginResponse response = new LoginResponse();
        response.setUser(Mapper.toTypeNoPassword(user));
        return ResponseEntity.ok(response);
    }

}
