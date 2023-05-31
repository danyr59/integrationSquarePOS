
package com.dani.api;

import com.dani.service.CatalogServiceImpl;
import com.squareup.square.models.CatalogObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("v1/items")
public class CatalogResource {
    CatalogServiceImpl a;
    
    public CatalogResource(){
        a = new CatalogServiceImpl();
    }
    
    @GET
    @Produces("application/json")
    public Response inventory(){
        List<CatalogObject> catalogo =  a.getCatalog();
        return Response.ok().status(Response.Status.OK).entity(catalogo).build();
    }
 
    
    //no usar ya que genera el mismo inventario
    @Path("/1")
    @GET
    @Produces
    public Response createInventory(){
        a.create_catalog();
        return Response.ok().status(Response.Status.CREATED).entity("listo").build();
    }

    
}
