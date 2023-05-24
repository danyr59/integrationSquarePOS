/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dani.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author danyr59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WraperUpdateOrder {

    
    Order order;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Order {

        String location_id;
        List<Fulfillments> fulfillments;
        String version;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Fulfillments {

        String type;
        ShipmentDetails shipment_details;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ShipmentDetails {

        Recipient recipient;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Recipient {

        String display_name;
        String email_address;
        String phone_number;
        Address address;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Address{
        String address_line_1;
        String address_line_2;
        String address_line_3;
        String administrative_district_level_1;
        String administrative_district_level_2;
        String administrative_district_level_3;
        String country;
        String locality;
        String postal_code;
        String sublocality;
        String sublocality_2;
        String sublocality_3;
        
        
    }

}
