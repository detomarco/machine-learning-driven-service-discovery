
package it.univaq.disim.numismatic.numismaticservice.client.model;

public enum CoinFieldType {

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

    CoinFieldType(String v) {
        value = v;
    }

    public static CoinFieldType fromValue(String v) {
        for (CoinFieldType c: CoinFieldType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
