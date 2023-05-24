/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.helpers;

import com.squareup.square.SquareClient;
import com.squareup.square.models.*;
import java.util.concurrent.CompletableFuture;

/**
 *
 * @author danyr59
 */
public class InformationSquare {

    String squareLocationId;
    String OrderId ;

    public InformationSquare(String id, String orderId) {
        this.squareLocationId = id;
        this.OrderId = orderId;
    }

    public CompletableFuture<RetrieveLocationResponse> getLocationInformation(
            SquareClient squareClient) {

        return squareClient.getLocationsApi().retrieveLocationAsync(squareLocationId)
                .thenApply(result -> {
                    return result;
                })
                .exceptionally(exception -> {
                    System.out.println("Failed to make the request");
                    System.out.printf("Exception: %s%n", exception.getMessage());
                    return null;
                });

    }

    public CompletableFuture<RetrieveOrderResponse> getOrderInformation(SquareClient squareClient) {
        return squareClient.getOrdersApi().retrieveOrderAsync(OrderId)
                .thenApply(result -> {
                    return result;
                })
                .exceptionally(exception -> {
                    System.out.println("Failed to make the request");
                    System.out.printf("Exception: %s%n", exception.getMessage());
                    return null;
                });
    }

}
