package com.assets.services.TableRows;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SeatingLayoutRow {
    private final SimpleIntegerProperty id;
    private final SimpleIntegerProperty ecoRows;
    private final SimpleIntegerProperty ecoCols;
    private final SimpleIntegerProperty busRows;
    private final SimpleIntegerProperty busCols;
    private final SimpleIntegerProperty firstRows;
    private final SimpleIntegerProperty firstCols;

    public SeatingLayoutRow(int id, int ecoRows, int ecoCols, int busRows, int busCols, int firstRows, int firstCols){
        this.id = new SimpleIntegerProperty(id);
        this.ecoRows = new SimpleIntegerProperty(ecoRows);
        this.ecoCols = new SimpleIntegerProperty(ecoCols);
        this.busRows = new SimpleIntegerProperty(busRows);
        this.busCols = new SimpleIntegerProperty(busCols);
        this.firstRows = new SimpleIntegerProperty(firstRows);
        this.firstCols = new SimpleIntegerProperty(firstCols);
    }

    public int getId() {
        return id.get();
    }

    public int getEcoRows() {
        return ecoRows.get();
    }

    public int getEcoCols() {
        return ecoCols.get();
    }

    public int getBusRows() {
        return busRows.get();
    }

    public int getBusCols() {
        return busCols.get();
    }

    public int getFirstRows() {
        return firstRows.get();
    }

    public int getFirstCols() {
        return firstCols.get();
    }
}
