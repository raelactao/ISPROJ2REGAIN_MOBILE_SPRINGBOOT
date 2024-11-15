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

    @lombok.NonNull
    String buyerName;

    ViewProductDTO product;

    @lombok.NonNull
    String offerValue;

    Boolean isAccepted;

    // add Boolean isOrdered

    @lombok.NonNull
    String sellerName;

}
