package com.dani.api;

import com.dani.model.*;
import com.dani.service.*;
import com.squareup.square.models.CreateCustomerRequest;
import com.squareup.square.models.Customer;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("v1/customers")
public class CustomerResource {

    /*
    @Inject
    private CustomerService serviceCustomer;
     */
    CustomerServiceImpl customer_service_impl;

    @GET
    @Produces("application/json")
    public Response getCustomers() {
        customer_service_impl = new CustomerServiceImpl();
        //customer_service_impl.create_connection();
        List<Customer> customers = customer_service_impl.list_of_customers();
        return Response.ok().status(Response.Status.OK).entity(customers).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response crear_customer(WraperCreateCustomer c_) {
       
        customer_service_impl = new CustomerServiceImpl();
        //customer_service_impl.create_connection();


        
        CreateCustomerRequest c =  customer_service_impl.create_customer_builder(c_.getGivenName(),
                c_.getFamilyName(),
                c_.getEmailAddress(),
                c_.getAddress().getAddressLine1(),
                c_.getAddress().getAddressLine2(),
                c_.getAddress().getLocality(),
                c_.getAddress().getAdministrativeDistrictLevel1(), 
                c_.getAddress().getPostalCode(), 
                c_.getAddress().getCountry(),
                c_.getPhoneNumber(), 
                c_.getReferenceId(), 
                c_.getNote());
        customer_service_impl.create_customer();
         
        return Response.ok().status(Response.Status.OK).entity(c).build();
    }
    
}
