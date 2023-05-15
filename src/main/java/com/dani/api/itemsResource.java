
package com.dani.api;

import com.dani.service.ItemsServiceImpl;
import com.squareup.square.models.CatalogObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("v1/items")
public class itemsResource {
    ItemsServiceImpl a;
    
    public itemsResource(){
        a = new ItemsServiceImpl();
    }
    
    @GET
    @Produces("application/json")
    public Response inventory(){
        List<CatalogObject> catalogo =  a.getCatalog();
        return Response.ok().status(Response.Status.OK).entity(catalogo).build();
    }
 
    @Path("/1")
    @GET
    @Produces
    public Response createInventory(){
        a.create_catalog();
        return Response.ok().status(Response.Status.CREATED).entity("listo").build();
    }

    
}
