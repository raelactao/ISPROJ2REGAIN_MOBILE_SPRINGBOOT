package com.isproj2.regainmobile.dto;

import com.isproj2.regainmobile.model.Favorite;
import com.isproj2.regainmobile.model.Product;

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
public class FavoriteDTO {

    private Integer favoriteID;

    @lombok.NonNull
    private Integer userID;

    @lombok.NonNull
    private Integer productID;

    @lombok.NonNull
    private Boolean isFavorite;

    public FavoriteDTO(Favorite fave) {
        this.favoriteID = fave.getFavoriteID();
        this.userID = fave.getUser().getUserID();
        this.productID = fave.getProduct().getProductID();
        this.isFavorite = fave.getIsFavorite();
    }

    public FavoriteDTO(Integer productId, Integer userId) {
        this.productID = productId;
        this.userID = userId;
        this.isFavorite = true;
    }

}
