package com.dani.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import com.dani.model.ResponseResult;
import com.dani.model.WraperCreateOrder;
import com.dani.model.WraperUpdateOrder;
import com.dani.service.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.core.Context;
import com.dani.model.ResponseResult;

import java.util.concurrent.ExecutionException;

@Path("v1/orders")
public class OrdersResource {

    //OrderServiceImpl service;
    public OrdersResource() {
        //service = new OrderServiceImpl();
    }

    @POST
    @Consumes("application/json")
    public Response create_order(WraperCreateOrder request, @Context HttpServletRequest httpRequest) throws InterruptedException, ExecutionException {
        String token = httpRequest.getHeader("Authorization");
        if (token.equals("") || token == null) {

            return Response.ok().status(Response.Status.CONFLICT).entity(new ResponseResult("FAILURE Token not found", null, null)).build();
        }
        System.out.println(token);
        OrderServiceImpl service = new OrderServiceImpl(token);
        

        System.out.println("en create order");
        System.out.println(request);
        ResponseResult result = service.createOrder(request);
        //SquareClient.shutdown();

        return Response.ok().status(Response.Status.CREATED).entity(result).build();
    }

    @PUT
    @Consumes("application/json")
    public Response update_payment(WraperUpdateOrder request,
            @QueryParam("order_id") String order_id,
            @QueryParam("location_id") String location_id,
            @Context HttpServletRequest httpRequest
    ) throws InterruptedException, ExecutionException {

        String token = httpRequest.getHeader("Authorization");
        if (token.equals("") || token == null) {
            
            return Response.ok().status(Response.Status.CONFLICT).entity(new ResponseResult("FAILURE Token not found", null, null)).build();
        }
        System.out.println(token);
        OrderServiceImpl service = new OrderServiceImpl(token);
        
        System.out.println("En update");
        System.out.println(order_id);
        System.out.println(request);
        System.out.println(location_id);
        ResponseResult result = service.updateOrder(request, order_id, location_id);
        
        
        //service.createPayment(request);
        //SquareClient.shutdown();

        return Response.ok().status(Response.Status.CREATED).entity(result).build();
    }

}
