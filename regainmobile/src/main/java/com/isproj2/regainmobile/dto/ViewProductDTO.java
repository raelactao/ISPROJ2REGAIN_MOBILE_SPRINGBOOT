package com.isproj2.regainmobile.dto;

import java.math.BigDecimal;

import com.isproj2.regainmobile.model.Product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
// @AllArgsConstructor
@NoArgsConstructor
// @RequiredArgsConstructor
public class ViewProductDTO {

    // product + address + user + favorite properties
    private Integer productID;

    private String productName;

    private String location; // address

    private String price;

    private String sellerUsername; // user

    private String description;

    private String weight;

    private String category; // category

    private Boolean canDeliver;

    private Boolean isFavorite; // favorite

    public ViewProductDTO(Integer productID, @NonNull String productName,
            @NonNull String city,
            @NonNull BigDecimal price, @NonNull String username,
            String description,
            @NonNull Double weight,
            String name, @NonNull Boolean canDeliver, @NonNull Boolean isFavorite) {
        this.productID = productID;
        this.productName = productName;
        this.location = city;
        this.price = price.toString();
        this.sellerUsername = username;
        this.description = description;
        this.weight = weight.toString();
        this.category = name;
        this.canDeliver = canDeliver;
        this.isFavorite = isFavorite;
    }

    public ViewProductDTO(Product product, Boolean fave) {
        this.productID = product.getProductID();
        this.productName = product.getProductName();
        this.location = product.getLocation().getCity();
        this.price = product.getPrice().toString();
        this.sellerUsername = product.getSeller().getUsername();
        this.description = product.getDescription();
        this.weight = product.getWeight().toString();
        this.category = product.getCategory().getName();
        this.canDeliver = product.getCanDeliver();
        this.isFavorite = fave;
    }

}