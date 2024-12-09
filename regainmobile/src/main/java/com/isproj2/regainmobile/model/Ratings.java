package com.isproj2.regainmobile.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "ratings")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Ratings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Integer ratingID;

    @ManyToOne
    @JoinColumn(name = "rated_user", referencedColumnName = "user_id")
    private User ratedUserID; // the seller

    @ManyToOne
    @JoinColumn(name = "rated_by_user", referencedColumnName = "user_id")
    private User ratedByUserID; //this is the current user

    @Column(name = "rate_value")
    private Integer rateValue;

    @ManyToOne
    @JoinColumn(name = "rate_tags", referencedColumnName = "rate_tags_id", nullable = true)
    private RateTag rateTags;

    @Column(name = "comments")
    private String comments;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "date_edited")
    private LocalDateTime dateEdited;
}
