/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dani.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.squareup.square.models.ApplicationDetails;
import com.squareup.square.models.BankAccountPaymentDetails;
import com.squareup.square.models.BuyNowPayLaterDetails;
import com.squareup.square.models.CardPaymentDetails;
import com.squareup.square.models.CashPaymentDetails;
import com.squareup.square.models.DeviceDetails;
import com.squareup.square.models.DigitalWalletDetails;
import com.squareup.square.models.ExternalPaymentDetails;
import com.squareup.square.models.Money;
import com.squareup.square.models.ProcessingFee;
import com.squareup.square.models.RiskEvaluation;
import io.apimatic.core.types.OptionalNullable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment_ {

    private String idempotency_key;
    private String source_id;
    private String reference_id;
    private String order_id;
    //private Money amount_money;
    private ExternalPaymentDetails external_details;
    private String location_id;

    //private OptionalNullable<Integer> orderVersion;
    //private OptionalNullable<List<String>> paymentIds;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Money {

        private Long amount;
        private String currency;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ExternalPaymentDetails {

        private String type;
        private String source;
        private String sourceId;
        private Money sourceFeeMoney;
    }

    /*
    private String id;
    private String created_at;
    private String updated_at;

    private Money tip_money;
    private Money total_money;
    private Money app_fee_money;
    private Money approvedMoney;
    private List<ProcessingFee> processing_fee;
    private Money refunded_money;
    private String status;
    private String delay_duration;
    private OptionalNullable<String> delay_action;
    private String delayed_until;
    private String source_type;
    private CardPaymentDetails card_details;
    private CashPaymentDetails cash_details;
    private BankAccountPaymentDetails bank_account_details;

    private DigitalWalletDetails wallet_details;
    private BuyNowPayLaterDetails buy_now_pay_later_details;
    

    private String customer_id;
    private String employee_id;
    private String team_member_id;
    private List<String> refund_ids;
    private RiskEvaluation risk_evaluation;
    private String buyer_email_address;
    private com.squareup.square.models.Address billing_address;
    private com.squareup.square.models.Address shipping_address;
    private String note;
    private String statement_description_identifier;
    private List<String> capabilities;
    private String receipt_number;
    private String receipt_url;
    private DeviceDetails device_details;
    private ApplicationDetails application_details;
    private OptionalNullable<String> version_token;
*/

}
