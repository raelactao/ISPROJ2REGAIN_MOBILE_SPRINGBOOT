package com.isproj2.regainmobile.dto;

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

}
