package com.foodtrucktracker.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "truckReviews")
public class TruckReview
    extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "truckId")
    @JsonIgnoreProperties(value = "reviews", allowSetters = true)
    private Truck truck;
}
