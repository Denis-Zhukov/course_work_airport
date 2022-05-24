package com.assets.services.Helpers;

public class ServicesPerDatesRow {
    private final String fullname;
    private final String fromTo;
    private final String airPlaneClass;

    public String getFullname() {
        return fullname;
    }

    public String getFromTo() {
        return fromTo;
    }

    public String getAirPlaneClass() {
        return airPlaneClass;
    }

    public ServicesPerDatesRow(String fullname, String fromTo, String airPlaneClass) {
        this.fullname = fullname;
        this.fromTo = fromTo;
        this.airPlaneClass = airPlaneClass;
    }
}
