package com.dani.service;

import com.helpers.ClientSquare;
import com.squareup.square.api.OrdersApi;
import com.squareup.square.models.CreateOrderRequest;
import com.squareup.square.models.Fulfillment;
import com.squareup.square.models.FulfillmentRecipient;
import com.squareup.square.models.FulfillmentShipmentDetails;
import com.squareup.square.models.Order;
import com.squareup.square.models.OrderLineItem;
import com.squareup.square.models.OrderLineItemModifier;
import com.squareup.square.models.UpdateOrderRequest;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
                //  .modifiers(modifiers)
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
                .locationId(locationId)
                .build();

        return order;
    }

    @Override
    public void createOrderBuilder(String modifierId, String quantityModifier, String quantityOrder, String itemVariationId, String location) {

        if (modifierId.isEmpty()) {
            OrderLineItemModifier orderLineItemM = this.createOrderLineModifier("", "");
            addOrderLineItemModifier(orderLineItemM);
        } else {
            OrderLineItemModifier orderLineItemM = this.createOrderLineModifier(modifierId, quantityModifier);
            addOrderLineItemModifier(orderLineItemM);
        }

        OrderLineItem orderLineItem = createOrderLineItem(quantityOrder, itemVariationId);
        addLineItems(orderLineItem);
        this.order = createOrder(location);
        //System.out.println(itemVariationId);

    }

    @Override
    public List<Order> createOrderRequest() {
        System.out.println(this.order);

        List<Order> order = new ArrayList<>();
        final Order orders;
        CreateOrderRequest body = new CreateOrderRequest.Builder()
                .order(this.order)
                .idempotencyKey(UUID.randomUUID().toString())
                .build();
        ordersApi.createOrderAsync(body)
                .thenAccept(result -> {

                    System.out.println("Successs!");
                    order.add(result.getOrder());

                })
                .exceptionally(exception -> {
                    System.out.println("Failed to make the request");
                    System.out.println(String.format("Exception: %s", exception.getMessage()));

                    System.out.println(String.format("Exception: %s", exception.getCause()));
                    return null;

                }).join();

        return order;

    }

    public void updateOrder() {
        FulfillmentRecipient recipient = new FulfillmentRecipient.Builder()
                .displayName("daniel")
                .emailAddress("danyelrange58@gmail.com")
                .phoneNumber("+584120713258")
                .build();

        FulfillmentShipmentDetails shipmentDetails = new FulfillmentShipmentDetails.Builder()
                .recipient(recipient)
                .build();

        Fulfillment fulfillment = new Fulfillment.Builder()
                .type("SHIPMENT")
                .shipmentDetails(shipmentDetails)
                .build();

        LinkedList<Fulfillment> fulfillments = new LinkedList<>();
        fulfillments.add(fulfillment);

        Order order = new Order.Builder("DD8J38D92DDQ2")
                .fulfillments(fulfillments)
                .version(1)
                .build();

        UpdateOrderRequest body = new UpdateOrderRequest.Builder()
                .order(order)
                .idempotencyKey("ee02eb3a-6a8d-455e-848d-27686ef87ddb")
                .build();

        ordersApi.updateOrderAsync("1iJuSUbd0hDnJv430Be5VshbPySZY", body)
                .thenAccept(result -> {
                    System.out.println("Success!");
                })
                .exceptionally(exception -> {
                    System.out.println("Failed to make the request");
                    System.out.println(String.format("Exception: %s", exception.getMessage()));
                    return null;
                });
    }

}
