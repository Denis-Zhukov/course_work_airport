package com.assets.services.TableRows;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AllFlightsRow {
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

    private final String number;

    public String getNumber() {
        return number;
    }

    private final Date boardingTime;

    public String getBoardingTime() {
        DateFormat outputFormatter = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        return outputFormatter.format(boardingTime);
    }

    public AllFlightsRow(String number, String fromCountry, String fromCity, String fromAirport, String toCountry, String toCity, String toAirport, Date boardingTime) {
        this.fromCountry = fromCountry;
        this.fromCity = fromCity;
        this.fromAirport = fromAirport;

        this.toCountry = toCountry;
        this.toCity = toCity;
        this.toAirport = toAirport;

        this.number = number;
        this.boardingTime = boardingTime;
    }

    public AllFlightsRow(String fromCountry, String fromCity, String fromAirport, String toCountry, String toCity, String toAirport, Date boardingTime) {
        this.fromCountry = fromCountry;
        this.fromCity = fromCity;
        this.fromAirport = fromAirport;

        this.toCountry = toCountry;
        this.toCity = toCity;
        this.toAirport = toAirport;

        this.boardingTime = boardingTime;
        this.number = null;
    }
}
