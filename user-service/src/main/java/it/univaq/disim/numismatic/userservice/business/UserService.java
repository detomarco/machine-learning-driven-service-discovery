package it.univaq.disim.numismatic.userservice.business;


import it.univaq.disim.numismatic.userservice.business.domain.User;

import java.util.List;

public interface UserService {

    User login(String username, String password);

    List<User> nearbyUsersByUser(String username, Double radius);

}
