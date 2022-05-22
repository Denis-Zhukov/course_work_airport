package com.assets.services.TableRows;

public class AllRoutesRow {
    private final String fromCountry;
    private final String fromCity;
    private final String fromAirport;

    public String getFromCountry() {
        return fromCountry;
    }

    public String getFromCity() {
        return fromCity;
    }

    public String getFromAirport() {
        return fromAirport;
    }

    private final String toCountry;
    private final String toCity;
    private final String toAirport;

    public String getToCountry() {
        return toCountry;
    }

    public String getToCity() {
        return toCity;
    }

    public String getToAirport() {
        return toAirport;
    }

    public AllRoutesRow(String fromCountry, String fromCity, String fromAirport, String toCountry, String toCity, String toAirport){
        this.fromCountry = fromCountry;
        this.fromCity = fromCity;
        this.fromAirport = fromAirport;

        this.toCountry = toCountry;
        this.toCity = toCity;
        this.toAirport = toAirport;
    }
}
