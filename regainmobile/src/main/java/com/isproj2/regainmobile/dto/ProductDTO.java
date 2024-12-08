package com.isproj2.regainmobile.dto;

import java.text.DecimalFormat;

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
    
    private byte[] image;

    @lombok.NonNull
    private Boolean canDeliver;

    private String status;

    public ProductDTO(Product product) {
        this.productID = product.getProductID();
        this.sellerID = product.getSeller().getUserID();
        this.productName = product.getProductName();
        this.description = product.getDescription();
        this.weight = new DecimalFormat("0.00").format(product.getWeight());
        this.location = product.getLocation().getAddressID();
        this.categoryID = product.getCategory().getCategoryID();
        this.price = product.getPrice().toString();
        this.image = product.getImage();
        this.canDeliver = product.getCanDeliver();
        this.status = product.getStatus();
    }

}
