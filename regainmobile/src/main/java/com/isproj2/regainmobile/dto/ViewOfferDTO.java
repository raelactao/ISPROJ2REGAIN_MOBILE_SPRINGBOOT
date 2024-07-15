package com.isproj2.regainmobile.dto;

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

    String buyerName;

    String offerValue;

    Boolean isAccepted;

    String sellerName;
    
} 
