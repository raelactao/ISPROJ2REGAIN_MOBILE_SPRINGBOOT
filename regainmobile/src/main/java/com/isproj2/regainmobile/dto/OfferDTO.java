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
public class OfferDTO {

    private Integer offerID;

    private Integer productID;

    private Integer buyerID;

    @lombok.NonNull
    private String offerValue;

    @lombok.NonNull
    private Boolean isAccepted;

    private Integer sellerID;

    // public OfferDTO(ViewOfferDTO viewoffer) {
        
    // }

}
