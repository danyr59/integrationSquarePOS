package com.dani.service;

import com.helpers.ClientSquare;
import com.squareup.square.api.OrdersApi;
import com.squareup.square.models.CreateOrderRequest;
import com.squareup.square.models.Order;
import com.squareup.square.models.OrderLineItem;
import com.squareup.square.models.OrderLineItemModifier;
import java.util.LinkedList;
import java.util.UUID;

/**
 *
 * @author danyr59
 */
public class OrderServiceImpl implements OrderService {

    LinkedList<OrderLineItemModifier> modifiers = new LinkedList<>();
    LinkedList<OrderLineItem> lineItems = new LinkedList<>();
    public OrdersApi ordersApi;
    Order order;

    public OrderServiceImpl() {
        ordersApi = ClientSquare.client.getOrdersApi();
    }

    @Override
    public OrderLineItemModifier createOrderLineModifier(String modifierId, String quantity) {
        OrderLineItemModifier orderLineItemModifier = new OrderLineItemModifier.Builder()
                .catalogObjectId(modifierId) //modifier id
                .quantity(quantity)
                .build();
        return orderLineItemModifier;

    }

    @Override
    public void addOrderLineItemModifier(OrderLineItemModifier ordenLineItemM) {
        modifiers.add(ordenLineItemM);
    }

    @Override
    public OrderLineItem createOrderLineItem(String quantity, String itemVariationId) {
        OrderLineItem orderLineItem = new OrderLineItem.Builder(quantity)
                .catalogObjectId(itemVariationId)
                .modifiers(modifiers)
                .build();
        return orderLineItem;

    }

    @Override
    public void addLineItems(OrderLineItem item) {
        lineItems.add(item);
    }
    
    

    @Override
    public Order createOrder(String locationId) {
        //crear order
        Order order = new Order.Builder(locationId)
                .lineItems(lineItems)
                .build();

        return order;
    }
    
    @Override
    public void createOrderBuilder(String modifierId, String quantityModifier, String quantityOrder, String itemVariationId, String location){
        if(!modifierId.isEmpty()){
            OrderLineItemModifier orderLineItemM =  this.createOrderLineModifier("", "");
            addOrderLineItemModifier(orderLineItemM);
        } else {
            OrderLineItemModifier orderLineItemM =  this.createOrderLineModifier(modifierId, quantityModifier);
            addOrderLineItemModifier(orderLineItemM);
        }
   
        
        
        OrderLineItem orderLineItem = createOrderLineItem(quantityOrder, itemVariationId );
        addLineItems(orderLineItem);
        this.order = createOrder(location);
        System.out.println("exitoso");
        
        
        
    }
    
    
    @Override
    public void createOrderRequest() {

        CreateOrderRequest body = new CreateOrderRequest.Builder()
                .order(this.order)
                .idempotencyKey(UUID.randomUUID().toString())
                .build();
        ordersApi.createOrderAsync(body)
                .thenAccept(result -> {
                    System.out.println("Successs!");
                })
                .exceptionally(exception -> {
                    System.out.println("Failed to make the request");
                    System.out.println(String.format("Exception: %s", exception.getMessage()));
                    return null;

                });
                
        
    }

}
