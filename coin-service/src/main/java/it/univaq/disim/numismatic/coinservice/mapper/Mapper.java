package it.univaq.disim.numismatic.coinservice.mapper;

import it.univaq.disim.numismatic.coinservice.business.domain.Coin;
import it.univaq.disim.numismatic.coinservice.controller.model.CoinFieldType;
import it.univaq.disim.numismatic.coinservice.controller.model.CoinType;
import it.univaq.disim.numismatic.coinservice.controller.model.ExampleCoinType;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Mapper {

    public static List<CoinType> toTypeByFields(List<Coin> model, @Nullable List<CoinFieldType> fields) {
        if (model == null) return null;
        if (fields == null || fields.isEmpty() || fields.stream().anyMatch(Objects::isNull)) {
            return model.stream().map(Mapper::toType).collect(Collectors.toList());
        } else {
            return model.stream().map(m -> toTypeByFields(m, fields)).collect(Collectors.toList());
        }
    }

    public static CoinType toTypeByFields(Coin model, List<CoinFieldType> fields) {
        if (model == null) return null;

        CoinType type = new CoinType();

        fields.stream()
                .filter(Objects::nonNull)
                .forEach(field -> {

                    switch (field) {
                        case VALUE:
                            type.setValue(model.getValue());
                            break;
                        case TYPE:
                            type.setType(model.getType());
                            break;
                        case COUNTRY:
                            type.setCountry(model.getCountry());
                            break;
                        case SERIES:
                            type.setSeries(model.getSeries());
                            break;
                        case YEAR_FROM:
                            type.setYearFrom(model.getYearFrom());
                            break;
                        case YEAR_TO:
                            type.setYearTo(model.getYearTo());
                            break;
                        case DIAMETER:
                            type.setDiameter(model.getDiameter());
                            break;
                        case THICKNESS:
                            type.setThickness(model.getThickness());
                            break;
                        case WEIGHT:
                            type.setWeight(model.getWeight());
                            break;
                        case IMAGE:
                            type.setImage(model.getImage());
                            break;

                    }
                });

        return type;
    }

    public static CoinType toType(Coin model) {
        if (model == null) return null;

        CoinType type = new CoinType();
        type.setCountry(model.getCountry());
        type.setType(model.getType());
        type.setSeries(model.getSeries());
        type.setValue(model.getValue());
        type.setDiameter(model.getDiameter());
        type.setThickness(model.getThickness());
        type.setWeight(model.getWeight());
        type.setYearFrom(model.getYearFrom());
        type.setYearTo(model.getYearTo());
        type.setImage(model.getImage());

        return type;
    }

    public static Coin toModel(ExampleCoinType type) {
        if (type == null) return null;

        return Coin.builder()
                .value(type.getValue())
                .type(type.getType())
                .country(type.getCountry())
                .series(type.getSeries())
                .yearFrom(type.getYearFrom())
                .yearTo(type.getYearTo())
                .diameter(type.getDiameter())
                .thickness(type.getThickness())
                .weight(type.getWeight())
                .build();
    }
}
