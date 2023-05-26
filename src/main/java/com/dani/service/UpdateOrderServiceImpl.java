/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dani.service;

import com.dani.model.ResponseResult;
import com.dani.model.WraperUpdateOrder;
import com.helpers.ClientSquare;
import com.helpers.InformationSquare;

//import com.squareup.square.api.;
import com.squareup.square.api.OrdersApi;
import com.squareup.square.exceptions.ApiException;
import com.squareup.square.models.Fulfillment;
import com.squareup.square.models.FulfillmentRecipient;
import com.squareup.square.models.FulfillmentShipmentDetails;
import com.squareup.square.models.Order;
import com.squareup.square.models.RetrieveLocationResponse;
import com.squareup.square.models.UpdateOrderRequest;
import com.squareup.square.models.Address;
import com.squareup.square.models.RetrieveOrderResponse;
import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author danyr59
 */
public class UpdateOrderServiceImpl {

    public OrdersApi ordersApi;

    public UpdateOrderServiceImpl() {
        ordersApi = ClientSquare.client.getOrdersApi();
    }

    public ResponseResult updateOrder(WraperUpdateOrder orderUpdate, String order_id, String location_id) throws InterruptedException, ExecutionException {
        InformationSquare information = new InformationSquare(location_id, order_id);
        RetrieveLocationResponse locationResponse = information.getLocationInformation(ClientSquare.client).get();
        String locationId = locationResponse.getLocation().getId();

        RetrieveOrderResponse orderResponse = information.getOrderInformation(ClientSquare.client).get();
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

        return ordersApi.updateOrderAsync(order_id, body)
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
