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
    /*

    public static void connection() {
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

        

        LocationsApi locationsApi = client.getLocationsApi();

        locationsApi.listLocationsAsync()
                .thenAccept(result -> {
                    for (Location l : result.getLocations()) {
                        System.out.printf(
                                "%s: %s, %s, %s\n",
                                l.getId(),
                                l.getName(),
                                l.getAddress().getAddressLine1(),
                                l.getAddress().getLocality());
                    }
                })
                .exceptionally(exception -> {
                    try {
                        throw exception.getCause();
                    } catch (ApiException ae) {
                        for (com.squareup.square.models.Error err : ae.getErrors()) {
                            System.out.println(err.getCategory());
                            System.out.println(err.getCode());
                            System.out.println(err.getDetail());
                        }
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                    return null;
                })
                .join();
        SquareClient.shutdown();
        
         */
   // }

}
