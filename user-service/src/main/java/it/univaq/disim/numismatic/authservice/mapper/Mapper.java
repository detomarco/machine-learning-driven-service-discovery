package it.univaq.disim.numismatic.authservice.mapper;


import it.univaq.disim.numismatic.authservice.business.domain.Authority;
import it.univaq.disim.numismatic.authservice.business.domain.City;
import it.univaq.disim.numismatic.authservice.business.domain.User;
import it.univaq.disim.numismatic.authservice.controller.model.CityType;
import it.univaq.disim.numismatic.authservice.controller.model.RolesType;
import it.univaq.disim.numismatic.authservice.controller.model.UserType;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    public static UserType toTypeNoPassword(User model) {
        if (model == null) return null;

        UserType type = new UserType();
        type.setName(model.getName());
        type.setSurname(model.getSurname());
        type.setUsername(model.getUsername());
        type.setEmail(model.getEmail());
        type.setCity(toType(model.getCity()));

        type.setEnabled(model.getEnabled());
        type.setRoles(toTypeAuthorityList(model.getAuthorities()));

        return type;
    }

    public static CityType toType(City model) {
        if (model == null) return null;

        CityType type = new CityType();
        type.setName(model.getName());
        if (model.getPoint() != null) {
            type.setLat(model.getPoint().getCoordinate().getX());
            type.setLng(model.getPoint().getCoordinate().getY());
        }
        return type;
    }

    private static RolesType toTypeAuthorityList(List<Authority> model) {
        if (model == null) return null;

        // convert authorities to string
        List<String> roles = model.stream()
                .map(Authority::getName)
                .map(Enum::toString)
                .collect(Collectors.toList());

        RolesType type = new RolesType();
        type.setRole(roles);
        return type;
    }

    public static List<UserType> toTypeUserList(List<User> model) {
        return model.stream()
                .map(Mapper::toTypeNoPassword)
                .collect(Collectors.toList());
    }
}
