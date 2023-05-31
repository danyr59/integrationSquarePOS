package com.dani.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import com.dani.model.ResponseResult;
import com.dani.model.WraperCreateOrder;
import com.dani.model.WraperUpdateOrder;
import com.dani.service.OrderServiceImpl;

import java.util.concurrent.ExecutionException;

@Path("v1/orders")
public class OrdersResource {

    OrderServiceImpl service;

    public OrdersResource() {
        service = new OrderServiceImpl();
    }

    @POST
    @Consumes("application/json")
    public Response create_order(WraperCreateOrder request) throws InterruptedException, ExecutionException {
        System.out.println("en create order");
        System.out.println(request);
        ResponseResult result = service.createOrder(request);

        return Response.ok().status(Response.Status.CREATED).entity(result).build();
    }

    @PUT
    @Consumes("application/json")
    public Response update_payment(WraperUpdateOrder request, @QueryParam("order_id") String order_id, @QueryParam("location_id") String location_id) throws InterruptedException, ExecutionException {
        System.out.println("En update");
        System.out.println(order_id);
        System.out.println(request);
        ResponseResult result = service.updateOrder(request, order_id, location_id);
        System.out.println(location_id);
        //service.createPayment(request);

        return Response.ok().status(Response.Status.CREATED).entity(result).build();
    }

}
