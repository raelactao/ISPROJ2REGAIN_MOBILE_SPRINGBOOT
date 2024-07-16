package com.isproj2.regainmobile.model;

import java.math.BigDecimal;

import com.isproj2.regainmobile.dto.OfferDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "offer")
@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id")
    private Integer offerID;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "user_id", nullable = false)
    private User buyer;

    @lombok.NonNull
    @Column(name = "offer_value")
    private BigDecimal offerValue;

    @lombok.NonNull
    @Column(name = "is_accepted")
    private Boolean isAccepted;

    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "user_id", nullable = false)
    private User seller;

    public Offer(OfferDTO offerDTO, User buyer, Product product, User seller) {
        this.offerID = offerDTO.getOfferID();
        this.product = product;
        this.buyer = buyer;
        this.offerValue = new BigDecimal(offerDTO.getOfferValue().toString());
        this.isAccepted = offerDTO.getIsAccepted();
        this.seller = seller;
    }

    
}
