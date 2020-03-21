package it.univaq.disim.numismatic.numismaticservice.business;

import it.univaq.disim.numismatic.numismaticservice.domain.User;

import java.util.List;

public interface UserService {

    /**
     * Update user information
     *
     * @param user user
     */
    void updateUser(User user);

    /**
     * Retrieve other users near an user
     *
     * @param username user name
     * @param radius   radius
     *
     * @return nearby users
     */
    List<User> nearbyUsers(String username, Double radius);

}
