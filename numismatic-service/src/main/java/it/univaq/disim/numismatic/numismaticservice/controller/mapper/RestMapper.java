package it.univaq.disim.numismatic.numismaticservice.controller.mapper;


import it.univaq.disim.numismatic.numismaticservice.controller.model.CityRest;
import it.univaq.disim.numismatic.numismaticservice.controller.model.CoinRest;
import it.univaq.disim.numismatic.numismaticservice.controller.model.RetrieveCoinRequest;
import it.univaq.disim.numismatic.numismaticservice.controller.model.UserRest;
import it.univaq.disim.numismatic.numismaticservice.domain.Authority;
import it.univaq.disim.numismatic.numismaticservice.domain.City;
import it.univaq.disim.numismatic.numismaticservice.domain.Coin;
import it.univaq.disim.numismatic.numismaticservice.domain.CoinField;
import it.univaq.disim.numismatic.numismaticservice.domain.User;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RestMapper {

    public static UserRest toRest(User model) {
        if (model == null) return null;

        return UserRest.builder()
                .username(model.getUsername())
                .name(model.getName())
                .surname(model.getSurname())
                .email(model.getEmail())
                .city(toRest(model.getCity()))
                .authorities(model.getAuthorities().stream()
                        .map(Authority::getName)
                        .map(Enum::toString)
                        .collect(Collectors.toList()))
                .build();
    }

    public static CityRest toRest(City model) {
        if (model == null) return null;

        CityRest type = new CityRest();
        type.setName(model.getName());
        type.setLat(model.getLat());
        type.setLng(model.getLng());
        return type;
    }

    public static CoinRest toRest(Coin model) {
        if (model == null) return null;
        CoinRest rest = new CoinRest();
        rest.setId(model.getId());
        rest.setCountry(model.getCountry());
        rest.setValue(model.getValue());
        rest.setType(model.getType());
        rest.setSeries(model.getSeries());
        rest.setYearFrom(model.getYearFrom());
        rest.setYearTo(model.getYearTo());
        rest.setDiameter(model.getDiameter());
        rest.setWeight(model.getWeight());
        rest.setThickness(model.getThickness());
        rest.setImage(model.getImage());
        return rest;
    }

    public static List<CoinRest> toCoinRestList(List<Coin> model) {
        if (model == null) return null;

        return model.stream()
                .map(RestMapper::toRest)
                .collect(Collectors.toList());
    }

    public static Coin toModel(RetrieveCoinRequest request) {
        if (request == null) return null;

        return Coin.builder()
                .value(request.getValue())
                .type(request.getType())
                .country(request.getCountry())
                .series(request.getSeries())
                .yearFrom(request.getYearFrom())
                .yearTo(request.getYearTo())
                .diameter(request.getDiameter())
                .thickness(request.getThickness())
                .weight(request.getWeight())
                .build();
    }

    public static List<CoinField> toCoinFieldModel(List<String> request) {
        if (request == null) return null;

        return request.stream()
                .map(CoinField::getEnum)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public static List<UserRest> toUserRestList(List<User> model) {
        if (model == null) return null;

        return model.stream().map(RestMapper::toRest).collect(Collectors.toList());
    }
}
