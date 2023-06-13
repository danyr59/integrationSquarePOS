package com.dani.service;

import com.dani.model.ResponseResult;
import com.dani.model.WraperCreateOrder;
import com.dani.model.WraperUpdateOrder;

import com.helpers.ClientSquareImpl;
import com.helpers.InformationSquare;

import com.squareup.square.api.OrdersApi;
import com.squareup.square.exceptions.ApiException;
import com.squareup.square.models.Address;
import com.squareup.square.models.CreateOrderRequest;
import com.squareup.square.models.Fulfillment;
import com.squareup.square.models.FulfillmentRecipient;
import com.squareup.square.models.FulfillmentShipmentDetails;
import com.squareup.square.models.Order;
import com.squareup.square.models.OrderLineItem;
import com.squareup.square.models.OrderLineItemModifier;
import com.squareup.square.models.OrderLineItemTax;
import com.squareup.square.models.RetrieveLocationResponse;
import com.squareup.square.models.RetrieveOrderResponse;
import com.squareup.square.models.UpdateOrderRequest;
import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author danyr59
 */
public class OrderServiceImpl implements OrderService {

    String token;
    LinkedList<OrderLineItemModifier> modifiers = new LinkedList<>();
    LinkedList<OrderLineItem> lineItems = new LinkedList<>();

    public OrdersApi ordersApi;

    public OrderServiceImpl(String token) {
        this.token = token;
    }

    @Override
    public ResponseResult createOrder(WraperCreateOrder order_) throws InterruptedException, ExecutionException {
        ClientSquareImpl client = new ClientSquareImpl(this.token);
        OrdersApi orders_api = client.getClient().getOrdersApi();

        LinkedList<OrderLineItemModifier> modifiers = new LinkedList<>();
        //LinkedList<OrderLineItem> lineItems = new LinkedList<>();

        RetrieveLocationResponse locationResponse = new InformationSquare(order_.getOrder().getLocation_id(), null).getLocationInformation(client.getClient()).get();
        String locationId = locationResponse.getLocation().getId();
        //System.out.println(locationId);
        order_.getOrder().getLine_items().stream().forEach(line_item -> {

            //añadir modificadores
            /*
           if(line_item.getModifiers() != null && !line_item.getModifiers().isEmpty()){
            line_item.getModifiers().stream().forEach(modifier -> {
                OrderLineItemModifier orderLineItemModifier = new OrderLineItemModifier.Builder()
                        .catalogObjectId(modifier.getCatalog_object_id())
                        .quantity(modifier.getQuantity())
                        .build();

                modifiers.add(orderLineItemModifier);
            });
            
            }
            
             */
            OrderLineItem orderLineItem = new OrderLineItem.Builder(line_item.getQuantity())
                    .catalogObjectId(line_item.getCatalog_object_id())
                    .itemType(line_item.getItem_type())
                    // .modifiers(modifiers)
                    .build();
            lineItems.add(orderLineItem);
        });

        LinkedList<OrderLineItemTax> taxes = new LinkedList<>();
        order_.getOrder().getTaxes().stream().forEach(tax -> {
            OrderLineItemTax orderLineItemTax = new OrderLineItemTax.Builder()
                    .catalogObjectId(tax.getCatalog_object_id())
                    .catalogVersion(Long.valueOf(tax.getCatalog_version()))
                    .build();
            taxes.add(orderLineItemTax);

        });

        Order order = new Order.Builder(locationId)
                .customerId(order_.getOrder().getCustomer_id())
                .lineItems(lineItems)
                .taxes(taxes)
                .state(order_.getOrder().getState())
                .ticketName(order_.getOrder().getTicket_name())
                .build();

        CreateOrderRequest body = new CreateOrderRequest.Builder()
                .order(order)
                .idempotencyKey(UUID.randomUUID().toString())
                .build();

        return orders_api.createOrderAsync(body)
                .thenApply(result -> {
                    return new ResponseResult("SUCCESS", result.getOrder().getId(), null);
                })
                .exceptionally(exception -> {
                    ApiException e = (ApiException) exception.getCause();
                    System.out.println("Failed to make the request");
                    System.out.println(String.format("Exception: %s", e.getMessage()));
                    return new ResponseResult("FAILURE", null, e.getErrors());
                }).join();
    }

    @Override
    public ResponseResult updateOrder(WraperUpdateOrder orderUpdate, String order_id, String location_id) throws InterruptedException, ExecutionException {
        ClientSquareImpl client = new ClientSquareImpl(this.token);

        OrdersApi orders_api = client.getClient().getOrdersApi();

        InformationSquare information = new InformationSquare(location_id, order_id);
         System.out.println(information);
        RetrieveLocationResponse locationResponse = information.getLocationInformation(client.getClient()).get();
       
        String locationId = locationResponse.getLocation().getId();

        RetrieveOrderResponse orderResponse = information.getOrderInformation(client.getClient()).get();
        Integer version = orderResponse.getOrder().getVersion();

        LinkedList<Fulfillment> fulfillments = new LinkedList<>();

        orderUpdate.getOrder().getFulfillments().stream().forEach(fulfillment -> {
            Address address = new Address.Builder()
                    .addressLine1(fulfillment.getShipment_details().getRecipient().getAddress().getAddress_line_1())
                    .addressLine2(fulfillment.getShipment_details().getRecipient().getAddress().getAddress_line_2())
                    .addressLine3(fulfillment.getShipment_details().getRecipient().getAddress().getAddress_line_3())
                    .locality(fulfillment.getShipment_details().getRecipient().getAddress().getLocality())
                    .sublocality(fulfillment.getShipment_details().getRecipient().getAddress().getSublocality())
                    .sublocality2(fulfillment.getShipment_details().getRecipient().getAddress().getSublocality_2())
                    .sublocality3(fulfillment.getShipment_details().getRecipient().getAddress().getSublocality_3())
                    .administrativeDistrictLevel1(fulfillment.getShipment_details().getRecipient().getAddress().getAdministrative_district_level_1())
                    .administrativeDistrictLevel2(fulfillment.getShipment_details().getRecipient().getAddress().getAdministrative_district_level_2())
                    .administrativeDistrictLevel3(fulfillment.getShipment_details().getRecipient().getAddress().getAdministrative_district_level_3())
                    .postalCode(fulfillment.getShipment_details().getRecipient().getAddress().getPostal_code())
                    .country(fulfillment.getShipment_details().getRecipient().getAddress().getCountry())
                    .build();

            FulfillmentRecipient recipient = new FulfillmentRecipient.Builder()
                    .displayName(fulfillment.getShipment_details().getRecipient().getDisplay_name())
                    .emailAddress(fulfillment.getShipment_details().getRecipient().getEmail_address())
                    .phoneNumber(fulfillment.getShipment_details().getRecipient().getPhone_number())
                    .address(address)
                    .build();

            FulfillmentShipmentDetails shipmentDetails = new FulfillmentShipmentDetails.Builder()
                    .recipient(recipient)
                    .build();

            Fulfillment fulfillment_ = new Fulfillment.Builder()
                    .type(fulfillment.getType())
                    .shipmentDetails(shipmentDetails)
                    .build();

            fulfillments.add(fulfillment_);
        });

        Order order = new Order.Builder(locationId)
                .fulfillments(fulfillments)
                .version(version)
                .build();

        UpdateOrderRequest body = new UpdateOrderRequest.Builder()
                .order(order)
                .idempotencyKey(UUID.randomUUID().toString())
                .build();

        return orders_api.updateOrderAsync(order_id, body)
                .thenApply(result -> {
                    return new ResponseResult("SUCCESS", order_id, null); //pay.add(result.getPayment());
                })
                .exceptionally(exception -> {
                    ApiException e = (ApiException) exception.getCause();
                    System.out.println("Failed to make the request");
                    System.out.println(String.format("Exception: %s", e.getMessage()));
                    return new ResponseResult("FAILURE", order_id, e.getErrors());
                }).join();
    }

}
