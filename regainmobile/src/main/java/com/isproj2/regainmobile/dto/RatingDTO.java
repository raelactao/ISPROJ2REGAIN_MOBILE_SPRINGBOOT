package com.isproj2.regainmobile.dto;

import java.time.LocalDateTime;

import com.isproj2.regainmobile.model.Ratings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingDTO {

    private Integer ratedUserId;
    private Integer ratedByUserId;
    private Integer rateValue;
    private String comments;
    private LocalDateTime dateCreated;
    private LocalDateTime dateEdited;

    public RatingDTO(Ratings rating) {
        this.ratedUserId = rating.getRatedUserID().getId();
        this.ratedByUserId = rating.getRatedByUserID().getId();
        this.rateValue = rating.getRateValue();
        this.comments = rating.getComments();
        this.dateCreated = rating.getDateCreated();
        this.dateEdited = rating.getDateEdited();
    }
}
