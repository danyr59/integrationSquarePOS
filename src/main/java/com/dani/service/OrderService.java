
package com.dani.service;

import com.squareup.square.models.Order;
import com.squareup.square.models.OrderLineItem;
import com.squareup.square.models.OrderLineItemModifier;
import java.util.List;

/**
 *
 * @author danyr59
 */
public interface OrderService {
    OrderLineItemModifier createOrderLineModifier(String catalogObjectId, String quantity);
    void addOrderLineItemModifier(OrderLineItemModifier ordenLineItemM);
    OrderLineItem createOrderLineItem(String quantity, String itemVariationId);
    void addLineItems(OrderLineItem item);
    Order createOrder(String locationId);
    List<Order> createOrderRequest();
    void createOrderBuilder(String modifierId, String quantityModifier, String quantityOrder, String itemVariationId, String location);
}
