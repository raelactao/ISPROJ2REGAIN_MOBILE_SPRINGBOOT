package com.isproj2.regainmobile.model;

import java.math.BigDecimal;
import java.util.Collection;

import com.isproj2.regainmobile.dto.ProductDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "products")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productID;

    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "user_id", nullable = false)
    private User seller;

    @lombok.NonNull
    @Column(name = "product_name")
    private String productName;

    @Column(name = "descript")
    private String description;

    @lombok.NonNull
    @Column(name = "weight")
    private Float weight;

    @lombok.NonNull
    @Column(name = "location")
    private Integer location;

    @lombok.NonNull
    @Column(name = "category")
    private Integer category;

    @lombok.NonNull
    @Column(name = "price")
    private BigDecimal price;

    // @Lob
    // @Column(name = "image")
    // private byte[] image;

    @lombok.NonNull
    @Column(name = "can_deliver")
    private Boolean canDeliver;

    @OneToMany(mappedBy = "product", fetch=FetchType.EAGER)
    private Collection<Favorite> favorite;

    @OneToOne(mappedBy = "product", fetch=FetchType.EAGER)
    private Collection<Order> order;

    public Product(ProductDTO productDTO, User seller) {
        this.productID = productDTO.getProductID();
        this.seller = seller;
        this.productName = productDTO.getProductName();
        this.description = productDTO.getDescription();
        this.weight = productDTO.getWeight();
        this.location = productDTO.getLocation();
        this.category = productDTO.getCategory();
        this.price = productDTO.getPrice();
        // this.image = productDTO.getPenaltyPoints();
        this.canDeliver = productDTO.getCanDeliver();
    }
}
