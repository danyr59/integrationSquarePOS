package com.dani.model;

import com.squareup.square.api.CustomersApi;
import com.squareup.square.models.CreateCustomerRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer_ {

    public String givenName;
    public String familyName;
    public String emailAddress;
    Address address;
    public String phoneNumber;
    public String referenceId;
    public String note;
    public CreateCustomerRequest body;
    public CustomersApi customers_api;
}
