package it.univaq.disim.numismatic.numismaticservice.common.mapper;

import it.univaq.disim.numismatic.numismaticservice.domain.User;

public class ModelMapper {

    public static User merge(User to, User from) {
        to.setUsername(from.getUsername());
        to.setName(from.getName());
        to.setUsername(from.getUsername());
        to.setEmail(from.getEmail());
        to.setCity(from.getCity());
        to.setAuthorities(from.getAuthorities());
        return to;
    }

}
