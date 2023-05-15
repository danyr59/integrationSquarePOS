
package com.dani.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 *
 * @author danyr59
 */
public class Order_ {
    String modifierId, quantityModifier,  quantityOrder, itemVariationId,  location;
    
}
