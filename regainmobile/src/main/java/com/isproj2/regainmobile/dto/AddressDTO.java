package com.isproj2.regainmobile.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    
    private Integer addressID;

    @lombok.NonNull
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
}
