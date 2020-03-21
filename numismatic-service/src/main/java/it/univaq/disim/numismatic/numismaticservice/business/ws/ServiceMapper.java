package it.univaq.disim.numismatic.numismaticservice.business.ws;

import it.univaq.disim.numismatic.numismaticservice.client.model.CityType;
import it.univaq.disim.numismatic.numismaticservice.client.model.CoinFieldType;
import it.univaq.disim.numismatic.numismaticservice.client.model.CoinFieldsType;
import it.univaq.disim.numismatic.numismaticservice.client.model.ExampleCoinType;
import it.univaq.disim.numismatic.numismaticservice.client.model.FieldValuesType;
import it.univaq.disim.numismatic.numismaticservice.client.model.RolesType;
import it.univaq.disim.numismatic.numismaticservice.client.model.UserType;
import it.univaq.disim.numismatic.numismaticservice.controller.model.CoinRest;
import it.univaq.disim.numismatic.numismaticservice.domain.Authority;
import it.univaq.disim.numismatic.numismaticservice.domain.AuthorityName;
import it.univaq.disim.numismatic.numismaticservice.domain.City;
import it.univaq.disim.numismatic.numismaticservice.domain.Coin;
import it.univaq.disim.numismatic.numismaticservice.domain.CoinField;
import it.univaq.disim.numismatic.numismaticservice.domain.CoinFieldValue;
import it.univaq.disim.numismatic.numismaticservice.domain.User;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class ServiceMapper {

    public static User toModel(UserType type) {
        if (type == null) return null;

        User model = new User();
        model.setName(type.getName());
        model.setSurname(type.getSurname());
        model.setUsername(type.getUsername());
        model.setPassword(type.getPassword());
        model.setEmail(type.getEmail());
        model.setCity(toModel(type.getCity()));
        model.setAuthorities(toModel(type.getRoles()));
        return model;
    }

    public static City toModel(CityType type) {
        if (type == null) return null;

        City model = new City();
        model.setName(type.getName());
        model.setLat(type.getLat());
        model.setLng(type.getLng());
        return model;
    }

    private static List<Authority> toModel(RolesType type) {
        if (type == null) return null;

        return type.getRole().stream()
                .map(AuthorityName::valueOf)
                .map(Authority::new)
                .collect(Collectors.toList());
    }

    public static Coin toModel(CoinRest type) {
        if (type == null) return null;

        return Coin.builder()
                .type(type.getType())
                .value(type.getValue())
                .country(type.getCountry())
                .series(type.getSeries())
                .yearFrom(type.getYearFrom())
                .yearTo(type.getYearTo())
                .diameter(type.getDiameter())
                .thickness(type.getThickness())
                .weight(type.getWeight())
                .image(type.getImage())
                .build();
    }

    public static List<Coin> toCoinRestModelList(List<CoinRest> type) {
        if (type == null) return null;

        return type.stream()
                .map(ServiceMapper::toModel)
                .collect(Collectors.toList());
    }


    public static ExampleCoinType toType(Coin model) {
        if (model == null) return null;

        ExampleCoinType type = new ExampleCoinType();
        type.setCountry(model.getCountry());
        type.setType(model.getType());
        type.setSeries(model.getSeries());
        type.setValue(model.getValue());
        type.setDiameter(model.getDiameter());
        type.setThickness(model.getThickness());
        type.setWeight(model.getWeight());
        type.setYearFrom(model.getYearFrom());
        type.setYearTo(model.getYearTo());
        return type;
    }


    public static List<User> toUserModelList(List<UserType> model) {
        if (model == null) return null;

        return model.stream()
                .map(ServiceMapper::toModel)
                .collect(Collectors.toList());
    }

    public static CoinFieldsType toCoinFieldTypeList(List<CoinField> model) {
        if (model == null) return null;

        CoinFieldsType type = new CoinFieldsType();

        List<CoinFieldType> fields = model.stream()
                .map(field -> {
                    try {
                        return CoinFieldType.fromValue(field.value());
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        type.setField(fields);
        return type;
    }

    public static List<CoinFieldValue> toCoinFieldValuesModel(FieldValuesType types) {
        if (types == null) return null;

        return types.getValue().stream()
                .map(type -> {
                    CoinFieldValue m = new CoinFieldValue();
                    m.setValue(type.getValue());
                    m.setNumber(type.getNumber());
                    return m;
                }).collect(Collectors.toList());
    }

}
