package com.assets.services;

import org.json.JSONObject;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

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
            if (queryResponse.isNull("accounts") || queryResponse.getJSONObject("accounts").getInt("affectedRows") == 0)
                throw new Exception("No updates rows");
        } catch (ConnectException e) {
            e.printStackTrace();
            System.out.println("Connection problem.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
