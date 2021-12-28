package com.foodtrucktracker.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "menuitemreviews")
public class MenuItemReview extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long menuItemReviewId;

    @ManyToOne
    @JoinColumn(name = "menuItemId")
    @JsonIgnoreProperties(value = "customerRatings", allowSetters = true)
    private MenuItem menuItem;
}
