package com.scenes.MaintenanceDispatcherPanel.SeatLayoutTemplates.AddSeatLayoutTemplatePanel;

import com.App;
import com.assets.components.NumberField;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.Helpers.SeatingLayout;
import com.assets.services.Requests;
import com.scenes.GeneralScenes.ModalWindow.ModalWindow;
import javafx.fxml.FXML;

import java.util.HashMap;
import java.util.Map;

public class AddSeatLayoutTemplateController {
    @FXML
    private NumberField firstClassRowsTextField, firstClassColsTextField;
    @FXML
    private NumberField businessClassRowsTextField, businessClassColsTextField;
    @FXML
    private NumberField economyClassRowsTextField, economyClassColsTextField;

    public void submit() {
        Map<Classes, SeatingLayout> classSeatingLayout = new HashMap<>();

        //Validation data
        String error = "";
        int firstClassRows = firstClassRowsTextField.getValue();
        int firstClassCols = firstClassColsTextField.getValue();
        if ((firstClassRows > 0 && firstClassCols == 0) || (firstClassRows == 0 && firstClassCols > 0)) {
            error += "Invalid number of rows and cols for the first class\n";
        } else if (firstClassRows != 0 && firstClassCols != 0)
            classSeatingLayout.put(Classes.first, new SeatingLayout(firstClassRows, firstClassCols));
        else classSeatingLayout.put(Classes.first, new SeatingLayout(0, 0));

        int businessClassRows = businessClassRowsTextField.getValue();
        int businessClassCols = businessClassColsTextField.getValue();
        if ((businessClassRows > 0 && businessClassCols == 0) || (businessClassRows == 0 && businessClassCols > 0)) {
            error += "Invalid number of rows and cols for the business class\n";
        } else if (businessClassRows != 0 && businessClassCols != 0)
            classSeatingLayout.put(Classes.business, new SeatingLayout(businessClassRows, businessClassCols));
        else classSeatingLayout.put(Classes.business, new SeatingLayout(0, 0));

        int economyClassRows = economyClassRowsTextField.getValue();
        int economyClassCols = economyClassColsTextField.getValue();
        if ((economyClassRows > 0 && economyClassCols == 0) || (economyClassRows == 0 && economyClassCols > 0)) {
            error += "Invalid number of rows and cols for the first class\n";
        } else if (economyClassRows != 0 && economyClassCols != 0)
            classSeatingLayout.put(Classes.economy, new SeatingLayout(economyClassRows, economyClassCols));
        else classSeatingLayout.put(Classes.economy, new SeatingLayout(0, 0));

        if (firstClassRows == 0 && firstClassCols == 0 &&
                businessClassRows == 0 && businessClassCols == 0 &
                economyClassRows == 0 && economyClassCols == 0)
            error += "None of the classes are filled\n";

        if (!error.equals("")) {
            ModalWindow.show("Error", error, ModalWindow.Icon.error);
            return;
        }

        //API Request
        try {
            String token = App.getAccessToken();
            int id = Requests.getNewSeatingLayoutId(token);
            for (Classes key : classSeatingLayout.keySet()) {
                SeatingLayout layout = classSeatingLayout.get(key);
                Requests.addSeatingLayout(id, key.getValue(), layout.getCountRows(), layout.getCountCols(), token);
            }

            //Reset fields and combobox
            firstClassRowsTextField.setText("");
            firstClassColsTextField.setText("");
            businessClassRowsTextField.setText("");
            businessClassColsTextField.setText("");
            economyClassRowsTextField.setText("");
            economyClassColsTextField.setText("");

            ModalWindow.show("Success", "Seat layout template has added", ModalWindow.Icon.success);
        } catch (ResponseException | NoServerResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "\nSeat layout template has not added", ModalWindow.Icon.error);
        }
    }

    private enum Classes {
        first(1),
        business(2),
        economy(3);

        private final int value;

        Classes(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
