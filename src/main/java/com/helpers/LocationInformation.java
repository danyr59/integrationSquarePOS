/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.helpers;

import com.squareup.square.SquareClient;
import com.squareup.square.models.RetrieveLocationResponse;
import java.util.concurrent.CompletableFuture;

/**
 *
 * @author danyr59
 */
public class LocationInformation {
    String squareLocationId = "DD8J38D92DDQ2";
    public LocationInformation(){
        
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
    
}
