package com.isproj2.regainmobile.model;

import com.isproj2.regainmobile.dto.FavoriteDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "favorites")
@Entity
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_id")
    private Integer favoriteID;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private Product product;

    @lombok.NonNull
    @Column(name = "is_favorite", columnDefinition = "tinyint(1) default 1")
    private Boolean isFavorite;

    public Favorite(FavoriteDTO favoriteDTO, User user, Product product) {
        this.favoriteID = favoriteDTO.getFavoriteID();
        this.user = user;
        this.product = product;
        this.isFavorite = favoriteDTO.getIsFavorite();
    }

    public Favorite(User user, Product product) {
        // this.favoriteID = favoriteDTO.getFavoriteID();
        this.user = user;
        this.product = product;
        this.isFavorite = true;
    }

}
