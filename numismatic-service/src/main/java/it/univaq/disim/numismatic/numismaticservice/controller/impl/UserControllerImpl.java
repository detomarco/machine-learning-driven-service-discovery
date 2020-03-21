package it.univaq.disim.numismatic.numismaticservice.controller.impl;

import it.univaq.disim.numismatic.numismaticservice.business.UserService;
import it.univaq.disim.numismatic.numismaticservice.controller.UserController;
import it.univaq.disim.numismatic.numismaticservice.controller.mapper.RestMapper;
import it.univaq.disim.numismatic.numismaticservice.controller.model.UsersResponse;
import it.univaq.disim.numismatic.numismaticservice.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Autowired
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UsersResponse> nearby(Double radius) {
        List<User> nearbyUsers;
        // retrieve authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        nearbyUsers = userService.nearbyUsers(authentication.getName(), radius);
        UsersResponse response = new UsersResponse();
        response.setUsers(RestMapper.toUserRestList(nearbyUsers));
        return ResponseEntity.ok(response);
    }

}
