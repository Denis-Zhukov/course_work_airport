package com.assets.services;

import com.scenes.AdminPanel.AdminPanel;
import com.scenes.EconomistPanel.EconomistPanel;
import com.scenes.MaintenanceDispatcherPanel.MaintenanceDispatcherPanel;
import com.scenes.StatisticsPanel.StatisticsPanel;
import com.scenes.TrafficCoordinationDispatcherPanel.TrafficCoordinationDispatcherPanel;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class Constants {
    public static final String authUrl = "http://127.0.0.1:3000/users/login";
    public static final String api = "http://127.0.0.1:3000/api/";


    public static final Pattern regexLogin = Pattern.compile("^[A-Za-z_][A-Za-z0-9_]{1,31}$");
    public static final Pattern regexPassword = Pattern.compile("^[A-Za-z0-9_$@%#&*^!]{5,32}$");

    public static final Pattern regexAirplaneNumber = Pattern.compile("^[A-Za-zА-Яа-яёЁ0-9-]{1,32}$");
    public static final Pattern regexAirplaneName = Pattern.compile("^[№#.:_A-Za-zА-Яа-яёЁ0-9-]{1,32}$");

    public static final Pattern regexCountryCityAirportName = Pattern.compile("^[a-zA-Z0-9 -]{2,}$");

    public static final Pattern regexFullName = Pattern.compile("(?=^.{0,40}$)^[a-zA-Z-]+\\s[a-zA-Z-]+$");
    public static final Pattern regexPassport = Pattern.compile("^(?!^0+$)[a-zA-Z0-9]{3,20}$");




    public static final HashMap<String, Consumer<Stage>> scenesByRoles = new HashMap<>() {{
        put("admin", AdminPanel::openChangingScene);
        put("maintenance dispatcher", MaintenanceDispatcherPanel::openChangingScene);
        put("traffic coordination dispatcher", TrafficCoordinationDispatcherPanel::openChangingScene);
        put("economist", EconomistPanel::openChangingScene);
        put("director", StatisticsPanel::openChangingScene);
    }};
}
