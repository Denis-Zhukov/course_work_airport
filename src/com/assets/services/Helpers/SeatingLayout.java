package com.assets.services.Helpers;

public class SeatingLayout {
    private final int countRows;
    private final int countCols;

    public int getCountRows() {
        return countRows;
    }

    public int getCountCols() {
        return countCols;
    }


    public SeatingLayout(int countRows, int countCols) {
        this.countRows = countRows;
        this.countCols = countCols;
    }
}
