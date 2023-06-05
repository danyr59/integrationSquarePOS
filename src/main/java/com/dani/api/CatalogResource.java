package com.dani.api;

import com.dani.service.CatalogServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.squareup.square.models.CatalogObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helpers.Utils.TypeCatalog;

@Path("v1/items")
public class CatalogResource {

    CatalogServiceImpl a;

    public CatalogResource() {
        a = new CatalogServiceImpl();
    }

    @GET
    @Produces("application/json")
    public Response inventory() throws JsonProcessingException {
        List<CatalogObject> catalogo = a.getCatalog(TypeCatalog.ALL);
        String jsonString = "";
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            jsonString = objectMapper.writeValueAsString(catalogo);
            

        } catch (JsonProcessingException e) {
            System.out.println("Error al serializar el objeto: " + e.getMessage());
        }

        return Response.ok().status(Response.Status.OK).entity(jsonString).build();
    }

    //no usar ya que genera el mismo inventario
    @Path("/1")
    @GET
    @Produces
    public Response createInventory() {
        a.create_catalog();
        return Response.ok().status(Response.Status.CREATED).entity("listo").build();
    }

}
