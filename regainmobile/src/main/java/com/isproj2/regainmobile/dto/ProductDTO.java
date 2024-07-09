package com.isproj2.regainmobile.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Integer productID;

    @lombok.NonNull
    private Integer sellerID;

    @lombok.NonNull
    private String productName;

    private String description;

    @lombok.NonNull
    private Double weight;

    @lombok.NonNull
    private Integer location;

    // changed to String so that I don't need to map ID from Flutter app and require
    // ID's in Flutter to match ID's in DB
    @lombok.NonNull
    private String category;

    @lombok.NonNull
    private BigDecimal price;
    // private byte[] image;

    @lombok.NonNull
    private Boolean canDeliver;
}
