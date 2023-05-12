
package com.dani.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    public String addressLine1;
    public String addressLine2;
    public String locality;
    public String AdministrativeDistrictLevel1;
    public String postalCode;
    public String country;
}
