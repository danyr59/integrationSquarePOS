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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.core.Context;

@Path("v1/items")
public class CatalogResource {

    //CatalogServiceImpl a = null;
    public CatalogResource() {
        //a = new CatalogServiceImpl();
    }

    @GET
    @Produces("application/json")
    public Response inventory(@Context HttpServletRequest httpRequest) throws JsonProcessingException {
        String token = httpRequest.getHeader("Authorization");
        if (token.equals("") || token == null) {

            return Response.ok().status(Response.Status.CONFLICT).entity("error falta token square").build();
        }
        CatalogServiceImpl a = new CatalogServiceImpl(token);

        System.out.println(token);

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
    public Response createInventory(@Context HttpServletRequest httpRequest) {
        String token = httpRequest.getHeader("Authorization");
        if (token.equals("") || token == null) {

            return Response.ok().status(Response.Status.CONFLICT).entity("error falta token square").build();
        }
        CatalogServiceImpl a = new CatalogServiceImpl(token);
        //a.create_catalog();
        return Response.ok().status(Response.Status.CREATED).entity("listo").build();
    }

}
