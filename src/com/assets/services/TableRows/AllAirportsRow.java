package com.assets.services.TableRows;

public class AllAirportsRow {
    private final String country;
    private final String city;
    private final String airport;

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAirport() {
        return airport;
    }

    public AllAirportsRow(String country, String city, String airport) {
        this.country = country;
        this.city = city;
        this.airport = airport;
    }
}
