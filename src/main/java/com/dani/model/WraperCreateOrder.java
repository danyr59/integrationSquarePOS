package com.dani.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author danyr59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WraperCreateOrder {

    Order order;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Order {

        String location_id, state, customer_id;
        List<lineItems> line_items;
        String ticket_name;
        List<Taxes> taxes;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class lineItems {

        String quantity, catalog_object_id, item_type;
        List<Modifiers> modifiers;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Taxes {

        String catalog_object_id;
        String catalog_version;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Modifiers {

        String catalog_object_id;
        String quantity;
    }
    /*
    String modifierId, quantityModifier, itemVariationId, quantityOrder,  location;
    //List<String> itemVariationId; 
    
     */
}
