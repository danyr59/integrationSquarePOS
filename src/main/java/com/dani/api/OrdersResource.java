
package com.dani.api;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.dani.model.Order_;
import com.dani.model.Orders_;
import com.dani.service.OrderServiceImpl;
import com.squareup.square.models.Order;
import java.util.List;

@Path("v1/orders")
public class OrdersResource {
    OrderServiceImpl orderService;
    
    public OrdersResource(){
        orderService = new OrderServiceImpl();
    }
    
    @GET
    @Produces("application/json")
    public Response getOrdens(){
        return Response.ok().status(Response.Status.OK).entity("hola").build();
    }
    
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response createOrder(Order_ order){
        
        System.out.println(order);
        
        orderService.createOrderBuilder(
                order.getModifierId(),
                order.getQuantityModifier(),
                order.getQuantityOrder(), 
                order.getItemVariationId(),
                order.getLocation());
        
        List<Order> orderResponse = orderService.createOrderRequest();
        System.out.println(orderResponse);
        
        
        return Response.ok().status(Response.Status.CREATED).entity(orderResponse).build();
    }
    @Path("/1")
    @POST
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    
    public Response createOrders(Orders_ order){
        System.out.println(order.getOrders());
        
        
        
        
        return Response.ok().status(Response.Status.CREATED).entity("hola").build();
    }
}
