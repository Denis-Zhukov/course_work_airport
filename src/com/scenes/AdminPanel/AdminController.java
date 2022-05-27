package com.scenes.AdminPanel;

import com.App;
import com.assets.services.InteractingWithWindow;
import com.scenes.AdminPanel.Roles.AddRolePanel.AddRolePanel;
import com.scenes.AdminPanel.Accounts.ChangeAccountPanel.ChangeAccountPanel;
import com.scenes.AdminPanel.Accounts.CreateAccountPanel.CreateAccountPanel;
import com.scenes.AdminPanel.Accounts.DeleteAccountPanel.DeleteAccountPanel;
import com.scenes.AdminPanel.Roles.DeleteRolePanel.DeleteRolePanel;
import com.scenes.AdminPanel.AccountsAndRoles.DepriveRolePanel.DepriveRolePanel;
import com.scenes.AdminPanel.AccountsAndRoles.SetAccountRolePanel.SetAccountRolePanel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminController {
    @FXML
    private Text loginText, roleText;

    //Accounts
    public void openCreateAccountWindow() {
        CreateAccountPanel.showModal();
    }
    public void openDeleteAccountWindow() {
        DeleteAccountPanel.showModal();
    }
    public void openChangeAccountWindow() {
        ChangeAccountPanel.showModal();
    }

    //Roles
    public void openAddingRoleWindow() {
        AddRolePanel.showModal();
    }
    public void openDeleteRoleWindow() {
        DeleteRolePanel.showModal();
    }

    //Roles and accounts
    public void openSetRoleWindow() {
        SetAccountRolePanel.showModal();
    }
    public void openDepriveRoleWindow() {
        DepriveRolePanel.showModal();
    }


    public void logOut() {
        App.resetAccount();
        Stage stage = (Stage) loginText.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(App.class.getResource("App.fxml"));
        InteractingWithWindow.changeScene(stage, loader);
        stage.centerOnScreen();
    }

    public void initialize() {
        loginText.setText(App.getUsername());
        roleText.setText(App.getRole());
    }
}
