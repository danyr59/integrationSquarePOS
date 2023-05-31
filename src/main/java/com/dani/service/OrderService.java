
package com.dani.service;

import com.dani.model.WraperCreateOrder;
import com.dani.model.ResponseResult;
import com.dani.model.WraperUpdateOrder;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author danyr59
 */
public interface OrderService {
    public ResponseResult createOrder(WraperCreateOrder order_) throws InterruptedException, ExecutionException;
    public ResponseResult updateOrder(WraperUpdateOrder orderUpdate, String order_id, String location_id) throws InterruptedException, ExecutionException;
}
