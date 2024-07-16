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
public class ViewOfferDTO {

    Integer offerID;

    Integer productID;

    @lombok.NonNull
    String buyerName;

    @lombok.NonNull
    String offerValue;

    @lombok.NonNull
    Boolean isAccepted;

    @lombok.NonNull
    String sellerName;


    
    
} 
