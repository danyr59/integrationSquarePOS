
package com.dani.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("v1/items")
public class itemsResource {
    
    @GET
    @Produces("application/json")
    public Response getOrdens(){
        return Response.ok().status(Response.Status.OK).entity("hola desde items").build();
    }
    
}
