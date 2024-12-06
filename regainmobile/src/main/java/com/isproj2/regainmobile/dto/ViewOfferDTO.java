package com.isproj2.regainmobile.dto;

import java.math.BigDecimal;

import com.isproj2.regainmobile.model.Offer;

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
    Boolean isOrdered;

    @lombok.NonNull
    String sellerName;

    public ViewOfferDTO(Offer offer, Boolean ordered) {
        this.offerID = offer.getOfferID();
        this.buyerName = offer.getBuyer().getUsername();
        this.product = new ViewProductDTO(offer.getProduct(), false);
        this.offerValue = offer.getOfferValue().toString();
        this.isAccepted = offer.getIsAccepted();
        this.isOrdered = ordered;
        this.sellerName = offer.getSeller().getUsername();

    }

}
