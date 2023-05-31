package com.helpers;

import com.squareup.square.Environment;
import com.squareup.square.SquareClient;
import com.squareup.square.api.LocationsApi;
import com.squareup.square.exceptions.ApiException;
import com.squareup.square.models.Location;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ClientSquare {

    static public SquareClient client;

    public ClientSquare() {
        InputStream inputStream
                = SquareClient.class.getResourceAsStream("/config.properties");

        System.out.println(inputStream);
        Properties prop = new Properties();
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            System.out.println("Error reading properties file");
            e.printStackTrace();
        }

        ClientSquare.client = new SquareClient.Builder()
                .accessToken(prop.getProperty("SQUARE_ACCESS_TOKEN"))
                .environment(Environment.SANDBOX)
                .build();

    }
    

}
