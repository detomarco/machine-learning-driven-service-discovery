package it.univaq.disim.numismatic.authservice.business.impl;


import it.univaq.disim.discovery.common.DiscoveryRestTemplate;
import it.univaq.disim.numismatic.authservice.business.UserService;
import it.univaq.disim.numismatic.authservice.business.domain.User;
import it.univaq.disim.numismatic.authservice.business.exception.BusinessException;
import it.univaq.disim.numismatic.authservice.business.exception.ErrorCode;
import it.univaq.disim.numismatic.authservice.business.repository.UserRepository;
import it.univaq.disim.numismatic.authservice.controller.model.LoginRequest;
import it.univaq.disim.numismatic.authservice.controller.model.LoginResponse;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.util.GeometricShapeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private DiscoveryRestTemplate discoveryRestTemplate;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> nearbyUsersByUser(String username, Double radius) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (!userOpt.isPresent()) {
            throw new BusinessException(ErrorCode.USER_NOT_EXISTS);
        }
        Geometry circle = createCircle(userOpt.get().getCity().getPoint(), radius);
        return userRepository.findNearbyCities(circle);
    }

    @Override
    public User login(String username, String password) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword(password);
        loginRequest.setUsername(username);
        LoginResponse loginResponse = discoveryRestTemplate.post("auth-service", "/login", loginRequest, LoginResponse.class);
        // authentication
        return authenticate(username, password);
    }

    private User authenticate(String username, String password) {
        Optional<User> user = userRepository.findByUsernameAndPassword(username, password);
        if (!user.isPresent()) {
            throw new BusinessException(ErrorCode.BAD_CREDENTIAL);
        }
        return user.orElse(null);
    }

    private Geometry createCircle(Point point, double radius) {
        GeometricShapeFactory shapeFactory = new GeometricShapeFactory();
        shapeFactory.setNumPoints(1000);
        shapeFactory.setCentre(point.getCoordinate());
        shapeFactory.setSize(radius * 2 / 88.1);
        return shapeFactory.createCircle();
    }

}
