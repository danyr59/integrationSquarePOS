package com.dani.api;

import com.dani.model.*;
import com.dani.service.*;
import com.squareup.square.models.CreateCustomerRequest;
import com.squareup.square.models.Customer;
import com.squareup.square.models.ListCustomersResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
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
    public Response getProducts() {
        customer_service_impl = new CustomerServiceImpl();
        customer_service_impl.create_connection();
        List<Customer> customers = customer_service_impl.list_of_customers();
        return Response.ok().status(Response.Status.OK).entity(customers).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response crear_customer(Customer_ c_) {
       
        customer_service_impl = new CustomerServiceImpl();
        customer_service_impl.create_connection();


        
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
    /*
            CreateCustomerRequest c =  customer_service_impl.create_customer_builder(c_.getGiven_name(),
                c_.getFamily_name(),
                c_.getEmailAddress(),
                c_.getAddress_line_1(),
                c_.getAddress_line_2(),
                c_.getLocality(),
                c_.getAdministrative_district_level_1(), 
                c_.getPostal_code(), 
                c_.getCountry(),
                c_.getPhone_number(), 
                c_.getReference_id(), 
                c_.getNote());
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("id") Integer id) {
        Product p = service.findById(id);
        return Response.ok().entity(p).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProduct(Product product) {
        Product p = service.save(product);
        return Response.ok().status(Response.Status.CREATED).entity(p).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") Integer id, Product product) {
        if (service.findById(id) != null) {
            product.setId(id);
            Product p = service.update(product);
            return Response.ok().status(Response.Status.OK).entity(p).build();
        } else {
            return Response.ok().status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") Integer id) {
        if (service.findById(id) != null) {
            service.delete(id);
            return Response.ok().status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.ok().status(Response.Status.NOT_FOUND).build();
        }
    }
     */
}
