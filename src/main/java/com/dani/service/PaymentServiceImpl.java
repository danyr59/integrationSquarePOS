package com.dani.service;

/**
 *
 * @author danyr59
 */
import com.dani.model.Payment_;
import com.helpers.ClientSquare;
import com.squareup.square.api.PaymentsApi;
import com.squareup.square.models.CreatePaymentRequest;
import com.squareup.square.models.ExternalPaymentDetails;
import com.squareup.square.models.Money;
import com.squareup.square.models.Payment;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PaymentServiceImpl {

    public PaymentsApi paymentsApi;

    public PaymentServiceImpl() {
        paymentsApi = ClientSquare.client.getPaymentsApi();
    }

    public Payment createPayment(Payment_ payment) {
        Money amountMoney = new Money.Builder()
                .amount(payment.getAmount_money().getAmount())
                .currency(payment.getAmount_money().getCurrency())
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

        List<Payment> pay = new ArrayList<>();
        paymentsApi.createPaymentAsync(body)
                .thenAccept(result -> {
                    pay.add(result.getPayment());
                    System.out.println("Success!");
                })
                .exceptionally(exception -> {
                    System.out.println("Failed to make the request");
                    System.out.println(String.format("Exception: %s", exception.getMessage()));
                    return null;
                }).join();
        System.out.println(pay.get(0));
        return pay.get(0);
    }

}
