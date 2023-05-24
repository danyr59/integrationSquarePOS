/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dani.model;

import java.util.List;

/**
 *
 * @author danyr59
 */
public class ResponseResult {

    private String title;
     private String orderId;

    public String getOrderId() {
        return orderId;
    }
   

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    

    private List<com.squareup.square.models.Error> errors;

    public ResponseResult(String t, String orderId ,List<com.squareup.square.models.Error> errorMessages) {
        this.title = t;
        this.orderId = orderId;
        this.errors = errorMessages;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setErrors(List<com.squareup.square.models.Error> errors) {
        this.errors = errors;
    }

    public List<com.squareup.square.models.Error> getErrors() {
        return this.errors;
    }
}
