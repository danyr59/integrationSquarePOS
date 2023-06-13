package com.dani.service;

import com.dani.model.WraperCreateCustomer;

import com.helpers.ClientSquareImpl;
import com.squareup.square.models.Customer;

import com.squareup.square.models.Address;
import com.squareup.square.models.CreateCustomerRequest;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    WraperCreateCustomer customer;
    

    public CustomerServiceImpl() {
        this.customer = new WraperCreateCustomer();
    }

    @Override
    public CreateCustomerRequest create_customer_builder(String gn, String fn, String ea,
            String al1, String al2,
            String l, String adl, String pc, String c,
            String pn, String rid, String n) {

        this.customer.body = new CreateCustomerRequest.Builder()
                .givenName(gn)
                .familyName(fn)
                .emailAddress(ea)
                .address(new Address.Builder()
                        .addressLine1(al1)
                        .addressLine2(al2)
                        .locality(l)
                        .administrativeDistrictLevel1(adl)
                        .postalCode(pc)
                        .country(c)
                        .build())
                .phoneNumber(pn)
                .referenceId(rid)
                .note(n)
                .build();
        this.customer.customers_api = ClientSquareImpl.client.getCustomersApi();
        //System.out.println(ClientSquare.client.getCustomersApi());
        return this.customer.body;

    }

    @Override
    public void create_customer() {
        this.customer.customers_api.createCustomerAsync(this.customer.body)
                .thenAccept(result -> {
                    System.out.printf("customer created:\n Given name = %s Family name = %s \n",
                            result.getCustomer().getGivenName(),
                            result.getCustomer().getFamilyName());
                })
                .exceptionally(exception -> {
                    // Your error-handling code here
                    System.out.println(exception);
                    return null;
                })
                .join();
    }

    @Override
    public List<Customer> list_of_customers() {
        this.customer.customers_api = ClientSquareImpl.client.getCustomersApi();
        // System.out.println(this.customer.customers_api);

        List<Customer> customers = new ArrayList<>();
        this.customer.customers_api.listCustomersAsync(null, null, null, null).thenAccept(result -> {
            // TODO success callback handler

            List<Customer> aux = result.getCustomers();
            for (Customer customer1 : aux) {
                customers.add(customer1);
            }

        }).exceptionally(exception -> {
            // TODO failure callback handler
            exception.printStackTrace();
            return null;
        }).join();
        return customers;

        /*
        ListCustomersResponse h = null;
        h = this.customer.customers_api.listCustomersAsync(null, null, null, null).join();
                

        return h.getCustomers();
         */
 /*
        System.out.println(h.getCustomers());
        ObjectMapper mapper = new ObjectMapper();
        String customers = "";
        try {
            customers =  mapper.writeValueAsString(h.getCustomers());

        } catch (JsonProcessingException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return customers;
         */
    }

}
