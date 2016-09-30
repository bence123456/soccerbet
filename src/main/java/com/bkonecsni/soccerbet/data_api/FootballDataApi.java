package com.bkonecsni.soccerbet.data_api;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

public class FootballDataApi {

    public String getTeams() throws IOException {
        String url = "http://api.football-data.org/v1/competitions/432/teams";
        String charset = "UTF-8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
//        String param1 = "value1";
//        String param2 = "value2";
//
//        String query = String.format("param1=%s&param2=%s",
//                URLEncoder.encode(param1, charset),
//                URLEncoder.encode(param2, charset));
//        URLConnection connection = new URL(url + "?" + query).openConnection();

        URLConnection connection = new URL(url).openConnection();
        connection.setRequestProperty("Accept-Charset", charset);
        InputStream response = connection.getInputStream();

        try (Scanner scanner = new Scanner(response)) {
            String responseBody = scanner.useDelimiter("\\A").next();
            System.out.println(responseBody);
        }

        return response.toString();
    }
}
