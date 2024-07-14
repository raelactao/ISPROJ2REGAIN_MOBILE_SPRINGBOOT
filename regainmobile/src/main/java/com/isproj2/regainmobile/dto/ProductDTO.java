package com.isproj2.regainmobile.dto;

import com.isproj2.regainmobile.model.Product;

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

    private Integer sellerID;

    @lombok.NonNull
    private String productName;

    private String description;

    @lombok.NonNull
    private String weight;

    @lombok.NonNull
    private Integer location;

    @lombok.NonNull
    private Integer categoryID;

    @lombok.NonNull
    private String price;
    // private byte[] image;

    @lombok.NonNull
    private Boolean canDeliver;

    public ProductDTO(Product product) {
        this.productID = product.getProductID();
        this.sellerID = product.getSeller().getUserID();
        this.productName = product.getProductName();
        this.description = product.getDescription();
        this.weight = product.getWeight().toString();
        this.location = product.getLocation().getAddressID();
        this.categoryID = product.getCategory().getCategoryID();
        this.price = product.getPrice().toString();
        this.canDeliver = product.getCanDeliver();
    }

}
