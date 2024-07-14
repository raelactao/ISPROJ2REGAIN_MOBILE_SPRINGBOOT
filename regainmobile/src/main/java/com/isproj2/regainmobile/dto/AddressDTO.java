package com.isproj2.regainmobile.dto;

import com.isproj2.regainmobile.model.Address;
import jakarta.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private Integer addressID;

    private String unitNumber;

    @lombok.NonNull
    private String street;

    @lombok.NonNull
    private String barangay;

    @lombok.NonNull
    private String city;

    @lombok.NonNull
    private String province;

    @lombok.NonNull
    private String zipCode;

    private Integer userID;

    public AddressDTO(Address address) {
        this.addressID = address.getAddressID();
        this.unitNumber = address.getUnitNumber();
        this.street = address.getStreet();
        this.barangay = address.getBarangay();
        this.city = address.getCity();
        this.province = address.getProvince();
        this.zipCode = address.getZipCode();
        this.userID = address.getUser().getUserID();
    }

}
