package com.dani.model;

import com.squareup.square.api.CustomersApi;
import com.squareup.square.models.CreateCustomerRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WraperCreateCustomer {

    public String givenName;
    public String familyName;
    public String emailAddress;
    
    public String phoneNumber;
    public String referenceId;
    public String note;
    //datos adicionales para operaciones internas
    public CreateCustomerRequest body;
    public CustomersApi customers_api;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Address {

        public String addressLine1;
        public String addressLine2;
        public String locality;
        public String AdministrativeDistrictLevel1;
        public String postalCode;
        public String country;
    }
    Address address;
}
