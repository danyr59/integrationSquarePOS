
package com.dani.model;

import com.squareup.square.models.Order;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danyr59
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders_ {
    
    //Order order_[] = new Order[100];
    List<Order_> orders = new ArrayList<>();
}
