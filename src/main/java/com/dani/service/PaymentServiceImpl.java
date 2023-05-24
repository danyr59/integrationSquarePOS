package com.dani.service;

/**
 *
 * @author danyr59
 */

import com.dani.model.Payment_;
import com.dani.model.ResponseResult;

import com.helpers.ClientSquare;
import com.helpers.LocationInformation;
import com.squareup.square.api.PaymentsApi;
import com.squareup.square.exceptions.ApiException;
import com.squareup.square.models.CreatePaymentRequest;
import com.squareup.square.models.ExternalPaymentDetails;
import com.squareup.square.models.Money;
import com.squareup.square.models.Payment;
import com.squareup.square.models.RetrieveLocationResponse;
//import com.squareup.square.models.RetrieveOr
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class PaymentServiceImpl {

    public PaymentsApi paymentsApi;
    

    public PaymentServiceImpl() {
        paymentsApi = ClientSquare.client.getPaymentsApi();
    }
    

    public ResponseResult createPayment(Payment_ payment) throws InterruptedException, ExecutionException {
        RetrieveLocationResponse locationResponse = new LocationInformation().getLocationInformation(ClientSquare.client).get();
        
        String  currency = locationResponse.getLocation().getCurrency();
        //System.out.println(locationResponse);
        Money amountMoney = new Money.Builder()
                .amount(payment.getAmount_money().getAmount())
                .currency(currency)
                .build();

        ExternalPaymentDetails externalDetails = new ExternalPaymentDetails.Builder(payment.getExternal_details().getType(), payment.getExternal_details().getSource())
                .sourceId(payment.getExternal_details().getSourceId())
                .build();

        CreatePaymentRequest body = new CreatePaymentRequest.Builder(payment.getSource_id(), UUID.randomUUID().toString())
                .amountMoney(amountMoney)
                .orderId(payment.getOrder_id())
                .locationId(payment.getLocation_id())
                .referenceId(payment.getReference_id())
                .externalDetails(externalDetails)
                .build();

        //List<Payment> pay = new ArrayList<>();

        return paymentsApi.createPaymentAsync(body)
                .thenApply(result -> {
                    return new ResponseResult("SUCCESS", null); //pay.add(result.getPayment());
                            //System.out.println("Success!");
                })
                .exceptionally(exception -> {
                    ApiException e= (ApiException) exception.getCause();
                    System.out.println("Failed to make the request");
                    System.out.println(String.format("Exception: %s", e.getMessage()));
                    return new ResponseResult("FAILURE", e.getErrors());
                }).join();
        //System.out.println(pay.get(0));
        //return pay.get(0);
    }

    
}
