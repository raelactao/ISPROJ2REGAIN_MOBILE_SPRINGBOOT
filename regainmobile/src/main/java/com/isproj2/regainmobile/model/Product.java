package com.isproj2.regainmobile.model;

import java.math.BigDecimal;
import java.util.Collection;
import com.isproj2.regainmobile.dto.ProductDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    @Column(name = "weight", length = 10, precision = 2)
    private Double weight;

    @ManyToOne
    @JoinColumn(name = "location", referencedColumnName = "address_id", nullable = false)
    private Address location;

    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "category_id", nullable = false)
    private Category category;

    @lombok.NonNull
    @Column(name = "price")
    private BigDecimal price;

    // @Lob
    // @Column(name = "image")
    // private byte[] image;

    @lombok.NonNull
    @Column(name = "can_deliver", columnDefinition = "tinyint(1) default 1")
    private Boolean canDeliver;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Collection<Favorite> favorite;

    @OneToOne(mappedBy = "product", fetch = FetchType.EAGER)
    private Order order;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private Collection<Offer> offer;

    @OneToMany(mappedBy = "reportedListing", fetch = FetchType.EAGER)
    private Collection<ListingReport> listingReport;

    public Product(ProductDTO productDTO, User seller, Address location, Category category) {
        this.productID = productDTO.getProductID();
        this.seller = seller;
        this.productName = productDTO.getProductName();
        this.description = productDTO.getDescription();
        this.weight = Double.parseDouble(productDTO.getWeight());
        this.location = location;
        this.category = category;
        this.price = new BigDecimal(productDTO.getPrice());
        // this.image = productDTO.getImage();
        this.canDeliver = productDTO.getCanDeliver();
    }
}
