package com.company;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UsersInLondon
{


    public static void main(String[] args)
    {
	   // Using java.net.http.HttpClient
       HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://bpdts-test-app.herokuapp.com/city/London/users")).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(UsersInLondon::parse)
                .join();
    }

    // Parse the returned JSON data
    public static String parse(String responseBody)
    {
        System.out.println("People who live in London");
        JSONArray usersLondon = new JSONArray((responseBody));
        for (int i = 0; i < usersLondon.length(); i++)
        {
            JSONObject userLondon = usersLondon.getJSONObject(i);
            int id = userLondon.getInt("id");
            String first_name = userLondon.getString("first_name");
            String last_name = userLondon.getString("last_name");
            String email = userLondon.getString("email");
            String ip_address = userLondon.getString("ip_address");
            double latitude = userLondon.getDouble("latitude");
            double longitude = userLondon.getDouble("longitude");
            System.out.println("id: " + id + " " + "first name: " + first_name + " " + "last name: " + last_name + " " + "email: " + email + " "
                    + "IP Address: " + ip_address + " " + "latitude: " + latitude + " " + "longtitude: " + longitude);
        }
         return null;
    }
}
