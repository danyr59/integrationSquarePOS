package com.dani.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.dani.model.Order_;
import com.dani.model.Orders_;
import com.dani.model.Payment_;
import com.dani.model.ResponseResult;
import com.dani.service.OrderServiceImpl;
import com.squareup.square.models.Order;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.json.JSONArray;
import org.json.JSONObject;

@Path("v1/orders")
public class OrdersResource {

    OrderServiceImpl service;

    public OrdersResource() {
        service = new OrderServiceImpl();
    }
    
    @POST
    @Consumes("application/json")
    public Response create_order(Order_ request) throws InterruptedException, ExecutionException{
        System.out.println("en create order");
        System.out.println(request);
        ResponseResult result = service.createOrder(request);
        //ResponseResult result = service.createPayment(request);
        //service.createPayment(request);
        
                
        return Response.ok().status(Response.Status.CREATED).entity(result).build();
    }


    /*

    OrderServiceImpl orderService;

    public OrdersResource() {
        orderService = new OrderServiceImpl();
    }

    @GET
    @Produces("application/json")
    public Response getOrdens() {
        return Response.ok().status(Response.Status.OK).entity("hola").build();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response createOrder(Order_ order) {

        System.out.println(order);

        orderService.createOrderBuilder(
                order.getModifierId(),
                order.getQuantityModifier(),
                order.getQuantityOrder(), 
                order.getItemVariationId(),
                order.getLocation()
               );
        
        List<Order> orderResponse = orderService.createOrderRequest();
        System.out.println(orderResponse.get(0));
        //JSONObject orderjson = new JSONObject(orderResponse.get(0));
        //orderjson.put("line_items", orderResponse.get(0).getLineItems() );
        //orderjson
        //System.out.println(orderjson.toString());
        
        
        
        
        return Response.ok().status(Response.Status.CREATED).entity(orderResponse.get(0)).build();
         
        //return Response.ok().status(Response.Status.CREATED).entity("hecho").build();
    }

    @Path("/1")
    @POST
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)

    public Response createOrders(Orders_ order) {
        System.out.println(order.getOrders());

        return Response.ok().status(Response.Status.CREATED).entity("hola").build();
    }

     */
}
