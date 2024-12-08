package com.isproj2.regainmobile.dto;

import java.text.DecimalFormat;

import com.isproj2.regainmobile.model.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
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
        this.image = product.getImage();

    }

    public ProductDTO(Integer productID2, Integer userID, String productName2,
            String description2,
            String string, Integer addressID, Integer categoryID2, String string2,
            Boolean canDeliver2,
            String status2, byte[] image2) {
        // TODO Auto-generated constructor stub
    }

}
