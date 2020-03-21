package it.univaq.disim.numismatic.numismaticservice.domain;

public enum CoinField {

    VALUE("value"),
    TYPE("type"),
    COUNTRY("country"),
    SERIES("series"),
    YEAR_FROM("yearFrom"),
    YEAR_TO("yearTo"),
    DIAMETER("diameter"),
    THICKNESS("thickness"),
    WEIGHT("weight"),
    IMAGE("image");

    private final String value;

    CoinField(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CoinField getEnum(String value) {
        for (CoinField coinType : values()) {
            if (coinType.value().toLowerCase().equals(value.toLowerCase())) {
                return coinType;
            }
        }
        return null;
    }

}
