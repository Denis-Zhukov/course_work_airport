package com.assets.services;

import com.assets.services.Exceptions.NoServerResponseException;
import com.assets.services.Exceptions.ResponseException;
import com.assets.services.Helpers.Airplane;
import com.assets.services.Helpers.SeatingLayout;
import com.assets.services.TableRows.AllAirportsRow;
import com.assets.services.TableRows.AllFlightsRow;
import com.assets.services.TableRows.AllRoutesRow;
import com.assets.services.TableRows.SeatingLayoutRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class Requests {
    public static JSONObject getJWTToken(String login, String password) throws NoServerResponseException {
        JSONObject json = new JSONObject() {{
            put("username", login);
            put("hashPassword", Security.hash(password));
        }};

        try {
            String requestBody = json.toString();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.authUrl))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .setHeader("Content-type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .timeout(Duration.ofSeconds(5))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            return new JSONObject(response.body()).put("statusServer", response.statusCode());
        } catch (ConnectException e) {
            throw new NoServerResponseException(e, "Connection problem");
        } catch (InterruptedException | IOException e) {
            throw new NoServerResponseException(e, "Server not responding");
        }
    }

    public static Map<String, Integer> getRoles(String token) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.api + "get_roles"))
                    .setHeader("Authorization", token)
                    .setHeader("Content-type", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            var res = new JSONObject(response.body());
            Map<String, Integer> roles = new HashMap<>();
            for (var r : res.getJSONArray("result")) {
                JSONObject role = ((JSONObject) r);
                roles.put(role.getString("role"), role.getInt("id"));
            }

            return roles;
        } catch (ConnectException e) {
            e.printStackTrace();
            System.out.println("Connection problem.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Shipping problem.");
        }
        return null;
    }

    public static String getRole(Integer id_account, String token) {
        JSONObject json = new JSONObject() {{
            put("id_account", id_account);
        }};
        try {
            String requestBody = json.toString();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.api + "get_role"))
                    .setHeader("Authorization", token)
                    .setHeader("Content-type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .timeout(Duration.ofSeconds(5))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            var res = new JSONObject(response.body());
            Map<String, Integer> roles = new HashMap<>();
            for (var r : res.getJSONArray("result")) {
                JSONObject role = ((JSONObject) r);
                return role.getString("role");
            }

        } catch (ConnectException e) {
            e.printStackTrace();
            System.out.println("Connection problem.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Shipping problem.");
        }
        return null;
    }

    public static void addRole(String role, String token) throws Exception {
        JSONObject json = new JSONObject() {{
            put("role", role);
        }};

        try {
            String requestBody = json.toString();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.api + "add_role"))
                    .setHeader("Authorization", token)
                    .setHeader("Content-type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            var AddAccountResponse = new JSONObject(response.body());
            if (AddAccountResponse.isNull("insertId"))
                throw new Exception(AddAccountResponse.getString("message"));
        } catch (ConnectException e) {
            e.printStackTrace();
            System.out.println("Connection problem.");
        }
    }

    public static Map<String, Integer> getAccounts(String token) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.api + "get_accounts"))
                    .setHeader("Authorization", token)
                    .setHeader("Content-type", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            var res = new JSONObject(response.body());
            Map<String, Integer> accounts = new HashMap<>();
            for (var acc : res.getJSONArray("result")) {
                JSONObject account = ((JSONObject) acc);
                accounts.put(account.getString("username"), account.getInt("id"));
            }
            return accounts;
        } catch (ConnectException e) {
            e.printStackTrace();
            System.out.println("Connection problem.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Shipping problem.");
        }
        return null;
    }

    public static void addAccount(String login, String password, Integer id_role, String token) throws Exception {
        JSONObject json = new JSONObject() {{
            put("username", login);
            put("hashPassword", Security.hash(password));
        }};

        try {
            String requestBody = json.toString();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.api + "add_account"))
                    .setHeader("Authorization", token)
                    .setHeader("Content-type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            var AddAccountResponse = new JSONObject(response.body());
            if (AddAccountResponse.isNull("insertId"))
                throw new Exception(AddAccountResponse.getString("message"));


            json = new JSONObject() {{
                put("id_account", AddAccountResponse.getInt("insertId"));
                put("id_role", id_role);
            }};
            requestBody = json.toString();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.api + "set_role"))
                    .setHeader("Authorization", token)
                    .setHeader("Content-type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            var SetRoleResponse = new JSONObject(response.body());
            if (SetRoleResponse.isNull("insertId"))
                throw new Exception(AddAccountResponse.getString("message"));

        } catch (ConnectException e) {
            e.printStackTrace();
            System.out.println("Connection problem.");
        }
    }

    public static void deleteAccount(Integer id_account, String token) throws Exception {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.api + "delete_account/" + id_account))
                    .setHeader("Authorization", token)
                    .setHeader("Content-type", "application/json")
                    .DELETE()
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            var deleteAccountResponse = new JSONObject(response.body());
            if (deleteAccountResponse.isNull("result") || deleteAccountResponse.getJSONObject("result").isNull("affectedRows")
                    || deleteAccountResponse.getJSONObject("result").getInt("affectedRows") == 0)
                throw new Exception(deleteAccountResponse.isNull("message") ? "Account could not be deleted" : deleteAccountResponse.getString("message"));
        } catch (ConnectException e) {
            e.printStackTrace();
            System.out.println("Connection problem.");
        }
    }

    public static void depriveRole(Integer id_account, String token) throws Exception {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.api + "deprive_role/" + id_account))
                    .setHeader("Authorization", token)
                    .setHeader("Content-type", "application/json")
                    .DELETE()
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            var deleteAccountResponse = new JSONObject(response.body());
            if (deleteAccountResponse.isNull("result") || deleteAccountResponse.getJSONObject("result").isNull("affectedRows")
                    || deleteAccountResponse.getJSONObject("result").getInt("affectedRows") == 0)
                throw new Exception(deleteAccountResponse.isNull("message") ? "Account could not be deleted" : deleteAccountResponse.getString("message"));
        } catch (ConnectException e) {
            e.printStackTrace();
            System.out.println("Connection problem.");
        }
    }

    public static void setRole(Integer idAccount, Integer idRole, String token) throws Exception {
        JSONObject json = new JSONObject() {{
            put("id_account", idAccount);
            put("id_role", idRole);
        }};

        try {
            String requestBody = json.toString();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.api + "set_role"))
                    .setHeader("Authorization", token)
                    .setHeader("Content-type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            var AddAccountResponse = new JSONObject(response.body());
            if (AddAccountResponse.isNull("insertId"))
                throw new Exception(AddAccountResponse.getString("message"));
        } catch (ConnectException e) {
            e.printStackTrace();
            System.out.println("Connection problem.");
        }
    }

    public static void deleteRole(Integer id_role, String token) throws Exception {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.api + "delete_role/" + id_role))
                    .setHeader("Authorization", token)
                    .setHeader("Content-type", "application/json")
                    .DELETE()
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            var deleteAccountResponse = new JSONObject(response.body());
            if (deleteAccountResponse.isNull("result") || deleteAccountResponse.getJSONObject("result").isNull("affectedRows")
                    || deleteAccountResponse.getJSONObject("result").getInt("affectedRows") == 0)
                throw new Exception(deleteAccountResponse.isNull("message") ? "Account could not be deleted" : deleteAccountResponse.getString("message"));
        } catch (ConnectException e) {
            e.printStackTrace();
            System.out.println("Connection problem.");
        }
    }

    public static void updateAccount(Integer id_account, String newUsername, String newPassword, Integer newId_Role, String token) {
        JSONObject json = new JSONObject() {{
            put("id_account", id_account);
            put("id_role", newId_Role);
            put("newUsername", newUsername);
            put("newHashPassword", newPassword.equals("") ? "" : Security.hash(newPassword));
        }};

        try {
            String requestBody = json.toString();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.api + "update_account"))
                    .setHeader("Authorization", token)
                    .setHeader("Content-type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            var queryResponse = new JSONObject(response.body());
            if (response.statusCode() != 200)
                throw new Exception(queryResponse.getString("message"));
        } catch (ConnectException e) {
            e.printStackTrace();
            System.out.println("Connection problem.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Completed
    public static List<Integer> getIdSeatingLayouts(String token) throws ResponseException, NoServerResponseException {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.api + "get_seating_layouts"))
                    .setHeader("Authorization", token)
                    .setHeader("Content-type", "application/json")
                    .GET()
                    .timeout(Duration.ofSeconds(5))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            serverStatusHandler(response.statusCode(), new JSONObject(response.body()));
            JSONObject res = new JSONObject(response.body());

            List<Integer> ids = new ArrayList<>();
            for (var r : res.getJSONArray("result")) {
                JSONObject id = ((JSONObject) r);
                ids.add(id.getInt("id"));
            }

            return ids;
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            throw new NoServerResponseException(e, "Connection problem.\n");
        }
    }

    public static Map<String, SeatingLayout> getSeatingLayout(Integer id, String token) {
        JSONObject json = new JSONObject() {{
            put("id", id);
        }};

        try {
            String requestBody = json.toString();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.api + "get_seating_layout"))
                    .setHeader("Authorization", token)
                    .setHeader("Content-type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            var res = new JSONObject(response.body());
            Map<String, SeatingLayout> layouts = new HashMap<>();
            for (var l : res.getJSONArray("result")) {
                JSONObject layout = ((JSONObject) l);
                layouts.put(layout.getString("class"), new SeatingLayout(layout.getInt("count_rows"), layout.getInt("count_cols")));
            }
            return layouts;
        } catch (ConnectException e) {
            e.printStackTrace();
            System.out.println("Connection problem.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Shipping problem.");
        }
        return null;
    }

    public static int getNewSeatingLayoutId(String token) throws ResponseException, NoServerResponseException {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.api + "get_new_seating_layout"))
                    .setHeader("Authorization", token)
                    .setHeader("Content-type", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            var result = new JSONObject(response.body());
            serverStatusHandler(response.statusCode(), result);

            return result.getJSONArray("result").getJSONObject(0).getInt("id");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new NoServerResponseException(e, "Connection problem");
        }
    }

    public static void addSeatingLayout(Integer id, Integer id_class, Integer countRows, Integer countCols, String token) throws ResponseException, NoServerResponseException {
        JSONObject json = new JSONObject() {{
            put("id", id);
            put("id_class", id_class);
            put("count_rows", countRows);
            put("count_cols", countCols);
        }};

        try {
            String requestBody = json.toString();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.api + "add_seating_layout"))
                    .setHeader("Authorization", token)
                    .setHeader("Content-type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            var result = new JSONObject(response.body());
            serverStatusHandler(response.statusCode(), result);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new NoServerResponseException(e, "Connection problem");
        }
    }

    //completed
    public static void addAirplane(Integer id_seating_layout, String name, String number, String token) throws NoServerResponseException, ResponseException {
        JSONObject json = new JSONObject() {{
            put("id_seating_layout", id_seating_layout);
            put("plane", name);
            put("number", number);
        }};

        try {
            String requestBody = json.toString();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.api + "add_plane"))
                    .setHeader("Authorization", token)
                    .setHeader("Content-type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            serverStatusHandler(response.statusCode(), new JSONObject(response.body()));
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            throw new NoServerResponseException(e, "Connection problem.\n");
        }
    }

    //////////////////////////////////////////////DeleteAirplanePanel
    //Completed
    public static Map<String, Integer> getAirplaneNumbers(String token) throws NoServerResponseException, ResponseException {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.api + "get_airplane_numbers"))
                    .setHeader("Authorization", token)
                    .setHeader("Content-type", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            Map<String, Integer> airplaneNumbersId = new HashMap<>();
            JSONObject body = new JSONObject(response.body());
            serverStatusHandler(response.statusCode(), body);

            JSONArray airplanesNumbersIdJson = body.getJSONArray("result");
            for (var airplaneNumber : airplanesNumbersIdJson) {
                JSONObject number = (JSONObject) airplaneNumber;
                airplaneNumbersId.put(number.getString("number"), number.getInt("id"));
            }

            return airplaneNumbersId;
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            throw new NoServerResponseException(e, "Connection problem\n");
        }
    }

    //Completed
    public static void deleteAirplane(Integer id, String token) throws NoServerResponseException, ResponseException {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.api + "delete_airplane/" + id))
                    .setHeader("Authorization", token)
                    .setHeader("Content-type", "application/json")
                    .DELETE()
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            var result = new JSONObject(response.body());
            serverStatusHandler(response.statusCode(), result);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new NoServerResponseException(e, "Connection problem");
        }
    }
    //////////////////////////////////////////////End DeleteAirplanePanel


    //////////////////////////////////////////////DeleteAirplanePanel
    //Completed
    public static Airplane getAirplaneById(Integer id, String token) throws NoServerResponseException, ResponseException {
        JSONObject json = new JSONObject() {{
            put("id", id);
        }};

        try {
            String requestBody = json.toString();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.api + "get_airplane_by_id"))
                    .setHeader("Authorization", token)
                    .setHeader("Content-type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .timeout(Duration.ofSeconds(5))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            var result = new JSONObject(response.body());
            serverStatusHandler(response.statusCode(), result);
            JSONArray array = result.getJSONArray("result");

            if (array.isEmpty())
                throw new ResponseException(null, "This airplane was not found\n");

            JSONObject jsonAirplane = array.getJSONObject(0);
            return new Airplane(jsonAirplane.getString("name"), jsonAirplane.getString("number"), jsonAirplane.getInt("id_seating_layout"));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new NoServerResponseException(e, "Connection problem");
        }
    }
    //////////////////////////////////////////////DeleteAirplanePanel


    public static void updateAirplane(Integer id, Integer id_seating_layout, String nameAirplane, String numberAirplane, String token) throws NoServerResponseException, ResponseException {
        JSONObject json = new JSONObject() {{
            put("id", id);
            put("id_seating_layout", id_seating_layout);
            put("name", nameAirplane);
            put("number", numberAirplane);
        }};

        try {
            String requestBody = json.toString();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.api + "update_airplane"))
                    .setHeader("Authorization", token)
                    .setHeader("Content-type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            var result = new JSONObject(response.body());
            serverStatusHandler(response.statusCode(), result);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new NoServerResponseException(e, "Connection problem");
        }
    }

    public static void deleteSeatLayoutTemplate(Integer id, String token) throws ResponseException, NoServerResponseException {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.api + "delete_seating_layout/" + id))
                    .setHeader("Authorization", token)
                    .setHeader("Content-type", "application/json")
                    .DELETE()
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            var result = new JSONObject(response.body());
            serverStatusHandler(response.statusCode(), result);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new NoServerResponseException(e, "Connection problem");
        }
    }

    public static ObservableList<SeatingLayoutRow> getAllSeatingLayoutTemplates(String token) throws ResponseException, NoServerResponseException {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.api + "get_all_seating_layouts"))
                    .setHeader("Authorization", token)
                    .setHeader("Content-type", "application/json")
                    .GET()
                    .timeout(Duration.ofSeconds(5))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            var result = new JSONObject(response.body());
            serverStatusHandler(response.statusCode(), result);
            JSONArray array = result.getJSONArray("result");
            if (array.isEmpty())
                throw new ResponseException(null, "This airplane was not found\n");

            Map<Integer, List<JSONObject>> group = new HashMap<>();
            for (Object obj : array) {
                int id = ((JSONObject) obj).getInt("id");
                if (!group.containsKey(id)) {
                    List<JSONObject> list = new ArrayList<>() {{
                        add((JSONObject) obj);
                    }};
                    group.put(id, list);
                } else {
                    group.get(id).add((JSONObject) obj);
                }
            }


            ObservableList<SeatingLayoutRow> data = FXCollections.observableArrayList();
            Set<Integer> keys = group.keySet();
            for (int key : keys) {
                List<JSONObject> layout = group.get(key);
                layout.sort(Comparator.comparingInt(o -> o.getInt("id_class")));
                data.add(new SeatingLayoutRow(
                        key,
                        layout.get(2).getInt("count_rows"),
                        layout.get(2).getInt("count_cols"),
                        layout.get(1).getInt("count_rows"),
                        layout.get(1).getInt("count_cols"),
                        layout.get(0).getInt("count_rows"),
                        layout.get(0).getInt("count_cols")
                ));
            }
            return data;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new NoServerResponseException(e, "Connection problem");
        } catch (IndexOutOfBoundsException e) {
            throw new ResponseException(e, "Data received from the server does not match what was expected\nContact your technical administrator to fix the problem\n");
        }
    }

    //Completed
    public static ObservableList<AllRoutesRow> getAllRoutes(String token) throws ResponseException, NoServerResponseException {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.api + "get_routes"))
                    .setHeader("Authorization", token)
                    .setHeader("Content-type", "application/json")
                    .GET()
                    .timeout(Duration.ofSeconds(5))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            var result = new JSONObject(response.body());
            serverStatusHandler(response.statusCode(), result);
            JSONArray array = result.getJSONArray("result");

            ObservableList<AllRoutesRow> data = FXCollections.observableArrayList();
            if (array.isEmpty()) return data;

            for (var o : array) {
                JSONObject object = (JSONObject) o;
                data.add(new AllRoutesRow(
                        object.getString("fromCountry"),
                        object.getString("fromCity"),
                        object.getString("fromAirport"),
                        object.getString("toCountry"),
                        object.getString("toCity"),
                        object.getString("toAirport")
                ));
            }

            return data;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new NoServerResponseException(e, "Connection problem");
        }
    }

    public static ObservableList getAllAirports(String token) throws ResponseException, NoServerResponseException {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.api + "get_airports"))
                    .setHeader("Authorization", token)
                    .setHeader("Content-type", "application/json")
                    .GET()
                    .timeout(Duration.ofSeconds(5))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            var result = new JSONObject(response.body());
            serverStatusHandler(response.statusCode(), result);
            JSONArray array = result.getJSONArray("result");

            ObservableList<AllAirportsRow> data = FXCollections.observableArrayList();
            if (array.isEmpty()) return data;

            for (var o : array) {
                JSONObject object = (JSONObject) o;
                data.add(new AllAirportsRow(
                        object.getString("country"),
                        object.getString("city"),
                        object.getString("airport")
                ));
            }

            return data;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new NoServerResponseException(e, "Connection problem");
        }
    }

    public static ObservableList getAllFlights(String token) throws ResponseException, NoServerResponseException {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.api + "get_flights"))
                    .setHeader("Authorization", token)
                    .setHeader("Content-type", "application/json")
                    .GET()
                    .timeout(Duration.ofSeconds(5))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            var result = new JSONObject(response.body());
            serverStatusHandler(response.statusCode(), result);
            JSONArray array = result.getJSONArray("result");

            ObservableList<AllFlightsRow> data = FXCollections.observableArrayList();
            if (array.isEmpty()) return data;

            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            for (var o : array) {
                JSONObject object = (JSONObject) o;
                data.add(new AllFlightsRow(
                        object.getString("number"),
                        object.getString("fromCountry"),
                        object.getString("fromCity"),
                        object.getString("fromAirport"),
                        object.getString("toCountry"),
                        object.getString("toCity"),
                        object.getString("toAirport"),
                        inputFormat.parse(object.getString("boarding_datetime"))
                ));
            }

            return data;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new NoServerResponseException(e, "Connection problem");
        } catch (ParseException e) {
            e.printStackTrace();
            throw new ResponseException(e, "Received data does not match the expected format\n");
        }
    }

    public static void addCountry(String country, String token) throws NoServerResponseException, ResponseException {
        JSONObject json = new JSONObject() {{
            put("country", country);
        }};

        try {
            String requestBody = json.toString();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.api + "add_country"))
                    .setHeader("Authorization", token)
                    .setHeader("Content-type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            var result = new JSONObject(response.body());
            serverStatusHandler(response.statusCode(), result);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new NoServerResponseException(e, "Connection problem");
        }
    }

    //Completed
    public static Map<String, Integer> getCountries(String token) throws ResponseException, NoServerResponseException {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.api + "get_countries"))
                    .setHeader("Authorization", token)
                    .setHeader("Content-type", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            var result = new JSONObject(response.body());
            serverStatusHandler(response.statusCode(), result);
            JSONArray array = result.getJSONArray("result");

            Map<String, Integer> countryId = new HashMap<>();
            for (var o : array) {
                JSONObject obj = (JSONObject) o;
                countryId.put(obj.getString("name"), obj.getInt("id"));
            }

            return countryId;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new NoServerResponseException(e, "Connection problem");
        }
    }

    //Completed
    public static void updateCountry(Integer id, String newCountryName, String token) throws ResponseException, NoServerResponseException {
        JSONObject json = new JSONObject() {{
            put("id", id);
            put("country", newCountryName);
        }};

        try {
            String requestBody = json.toString();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.api + "update_country"))
                    .setHeader("Authorization", token)
                    .setHeader("Content-type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            var result = new JSONObject(response.body());
            serverStatusHandler(response.statusCode(), result);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new NoServerResponseException(e, "Connection problem");
        }
    }

    //Completed
    public static void deleteCountry(int id, String token) throws ResponseException, NoServerResponseException {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.api + "delete_country/" + id))
                    .setHeader("Authorization", token)
                    .setHeader("Content-type", "application/json")
                    .DELETE()
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            var result = new JSONObject(response.body());
            serverStatusHandler(response.statusCode(), result);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new NoServerResponseException(e, "Connection problem");
        }
    }

    //Completed
    public static void addCity(String city, int idCountry, String token) throws ResponseException, NoServerResponseException {
        JSONObject json = new JSONObject() {{
            put("id_country", idCountry);
            put("city", city);
        }};

        try {
            String requestBody = json.toString();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(Constants.api + "add_city"))
                    .setHeader("Authorization", token)
                    .setHeader("Content-type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            serverStatusHandler(response.statusCode(), new JSONObject(response.body()));
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            throw new NoServerResponseException(e, "Connection problem.\n");
        }
    }

    //Status code handler
    //Completed
    private static void serverStatusHandler(int status, JSONObject response) throws NoServerResponseException, ResponseException {
        switch (status) {
            case 500 -> throw new ResponseException(null, "Suspended error: database problem.\nServer: " + response.getString("message"));
            case 410 -> throw new NoServerResponseException(null, "Suspended error: no entry to update.\nServer: " + response.getString("message"));
            case 404 -> throw new NoServerResponseException(null, "Error: Invalid API request");
            case 403, 400 -> throw new ResponseException(null, "Server: " + response.getString("message"));
        }
    }
}