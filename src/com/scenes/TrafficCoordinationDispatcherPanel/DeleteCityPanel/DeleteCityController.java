package com.scenes.TrafficCoordinationDispatcherPanel.DeleteCityPanel;

import com.App;
import com.assets.components.AutoCompleteComboBoxListener;
import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.Requests;
import com.scenes.ModalWindow.ModalWindow;
import javafx.scene.control.ComboBox;

public class DeleteCityController {
    public ComboBox<String> citiesComboBox;

    public void submit() {
        String city = citiesComboBox.getValue();
        city = city == null ? "" : city.trim();

        String error = "";
        if (!DeleteCityPanel.citiesId.containsKey(city))
            error += "A city has been selected that is not in the dropdown list\n";

        if (!error.equals("")) {
            ModalWindow.show("Error", error, ModalWindow.Icon.error);
            return;
        }

        try {
            int id = DeleteCityPanel.citiesId.get(city);
            Requests.deleteCity(id, App.getAccessToken());
            DeleteCityPanel.citiesId.remove(city);
            citiesComboBox.getItems().remove(city);
            citiesComboBox.setValue("");
            ModalWindow.show("Success", "City has deleted", ModalWindow.Icon.success);
        } catch (NoServerResponseException | ResponseException e) {
            ModalWindow.show("Error", e.getSuspendedMessage() + "\nCity has not deleted", ModalWindow.Icon.error);
        }
    }

    public void initialize(){
        new AutoCompleteComboBoxListener<>(citiesComboBox);
    }
}
