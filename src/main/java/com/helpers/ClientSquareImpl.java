package com.helpers;

import com.squareup.square.Environment;
import com.squareup.square.SquareClient;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ClientSquareImpl {

    static public SquareClient client;
    public SquareClient client1;

    public ClientSquareImpl() {
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

        ClientSquareImpl.client = new SquareClient.Builder()
                .accessToken(prop.getProperty("SQUARE_ACCESS_TOKEN"))
                .environment(Environment.SANDBOX)
                .build();

    }

    public ClientSquareImpl(String token) {

        client1 = new SquareClient.Builder()
                .accessToken(token)
                .environment(Environment.SANDBOX)
                .build();

    }

    public SquareClient getClient() {
        return this.client1;
    }

}
