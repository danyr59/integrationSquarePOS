/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dani.api;

import com.dani.model.ResponseResult;
import com.dani.model.WraperUpdateOrder;
import com.dani.service.UpdateOrderServiceImpl;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author danyr59
 */
@Path("v1/update-order")
public class UpdatePayment {
    UpdateOrderServiceImpl service = new UpdateOrderServiceImpl();

    @PUT
    @Consumes("application/json")
    public Response update_payment(WraperUpdateOrder request, @QueryParam("order_id")String order_id) throws InterruptedException, ExecutionException {
        System.out.println("En update");
        System.out.println(order_id);
        System.out.println(request);
        ResponseResult result = service.updateOrder(request, order_id);
        //service.createPayment(request);

        return Response.ok().status(Response.Status.CREATED).entity(result).build();
    }
}
