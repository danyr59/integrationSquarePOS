/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dani.service;


import com.dani.model.ResponseResult;
import com.dani.model.WraperCreatePayment;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author danyr59
 */
public interface PaymentService {

    public ResponseResult createPayment(WraperCreatePayment payment) throws InterruptedException, ExecutionException;
}
