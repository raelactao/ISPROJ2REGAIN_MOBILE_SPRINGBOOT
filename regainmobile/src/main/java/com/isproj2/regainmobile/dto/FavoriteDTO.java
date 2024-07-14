package com.isproj2.regainmobile.dto;

import com.isproj2.regainmobile.model.Favorite;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteDTO {

    private Integer favoriteID;

    private Integer userID;

    private Integer productID;

    @lombok.NonNull
    private Boolean isFavorite;

    public FavoriteDTO(Favorite fave) {
        this.favoriteID = fave.getFavoriteID();
        this.userID = fave.getUser().getUserID();
        this.productID = fave.getProduct().getProductID();
        this.isFavorite = fave.getIsFavorite();
    }

}
