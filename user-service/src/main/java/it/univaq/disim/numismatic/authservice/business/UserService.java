package it.univaq.disim.numismatic.authservice.business;


import it.univaq.disim.numismatic.authservice.business.domain.User;

import java.util.List;

public interface UserService {

    User login(String username, String password);

    List<User> nearbyUsersByUser(String username, Double radius);

}
