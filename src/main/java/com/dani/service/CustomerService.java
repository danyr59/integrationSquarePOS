
package com.dani.service;

import com.squareup.square.models.CreateCustomerRequest;
import com.squareup.square.models.Customer;
import java.util.List;


public interface CustomerService {
    CreateCustomerRequest create_customer_builder(String gn, String fn, String ea,
            String al1, String al2,
            String l, String adl, String pc, String c,
            String pn, String rid, String n);
    void create_customer();
    List<Customer> list_of_customers();
    
}
