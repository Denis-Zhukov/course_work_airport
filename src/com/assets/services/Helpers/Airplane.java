package com.assets.services.Helpers;

public class Airplane {
    private String name;
    private String number;
    private Integer idSeatingLayout;

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public Integer getIdSeatingLayout() {
        return idSeatingLayout;
    }

    public Airplane(String name, String number, Integer idSeatingLayout) {
        this.name = name;
        this.number = number;
        this.idSeatingLayout = idSeatingLayout;
    }
}
