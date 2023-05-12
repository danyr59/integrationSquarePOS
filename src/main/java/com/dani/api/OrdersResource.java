
package com.dani.api;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("v1/orders")
public class OrdersResource {
    
    @GET
    @Produces("application/json")
    public Response getOrdens(){
        return Response.ok().status(Response.Status.OK).entity("hola").build();
    }
}
