package com.assets.services.TableRows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.math.BigDecimal;
import java.util.Date;
import java.util.function.Consumer;

public class CustomerRow {
    private final String from;
    private final String to;
    private final Date dateTime;
    private final String firstCost;
    private final String businessCost;
    private final String economyCost;

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public String getFirstCost() {
        return firstCost;
    }

    public String getBusinessCost() {
        return businessCost;
    }

    public String getEconomyCost() {
        return economyCost;
    }

    private final Button book;

    public Button getBook() {
        return book;
    }

    public CustomerRow(String from, String to, Date dateTime, String firstCost, String businessCost, String economyCost, EventHandler buttonAction) {
        this.from = from;
        this.to = to;
        this.dateTime = dateTime;
        this.firstCost = firstCost;
        this.businessCost = businessCost;
        this.economyCost = economyCost;

        book = new Button("Book");
        book.setOnAction(buttonAction);
    }
}
