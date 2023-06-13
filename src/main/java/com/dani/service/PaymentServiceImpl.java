package com.dani.service;

/**
 *
 * @author danyr59
 */


import com.dani.model.ResponseResult;
import com.dani.model.WraperCreatePayment;


import com.helpers.ClientSquareImpl;
import com.helpers.InformationSquare;

import com.squareup.square.api.PaymentsApi;
import com.squareup.square.exceptions.ApiException;
import com.squareup.square.models.CreatePaymentRequest;
import com.squareup.square.models.ExternalPaymentDetails;
import com.squareup.square.models.Money;
import com.squareup.square.models.RetrieveLocationResponse;
import com.squareup.square.models.RetrieveOrderResponse;
//import com.squareup.square.models.RetrieveOr
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class PaymentServiceImpl implements PaymentService{

    String token;
    //public PaymentsApi paymentsApi;
    

    
    public PaymentServiceImpl(String token){
        this.token = token;
    }
    
    @Override
    public ResponseResult createPayment(WraperCreatePayment payment) throws InterruptedException, ExecutionException {
        ClientSquareImpl client = new ClientSquareImpl(this.token);
        PaymentsApi paymentsApi = client.getClient().getPaymentsApi();
        
        InformationSquare information = new InformationSquare(payment.getLocation_id(), payment.getOrder_id());
        RetrieveLocationResponse locationResponse = information.getLocationInformation(client.getClient()).get();
        
        RetrieveOrderResponse orderResponse = information.getOrderInformation(client.getClient()).get();
        String amount = String.valueOf(orderResponse.getOrder().getTotalMoney().getAmount());
        System.out.println(orderResponse.getOrder());
        
        String  currency = locationResponse.getLocation().getCurrency();
        //System.out.println(locationResponse);
        Money amountMoney = new Money.Builder()
                .amount(Long.valueOf(amount))
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
                    return new ResponseResult("SUCCESS", null, null); //pay.add(result.getPayment());
                            //System.out.println("Success!");
                })
                .exceptionally(exception -> {
                    ApiException e= (ApiException) exception.getCause();
                    System.out.println("Failed to make the request");
                    System.out.println(String.format("Exception: %s", e.getMessage()));
                    return new ResponseResult("FAILURE", null , e.getErrors());
                }).join();
        //System.out.println(pay.get(0));
        //return pay.get(0);
    }

    
}
