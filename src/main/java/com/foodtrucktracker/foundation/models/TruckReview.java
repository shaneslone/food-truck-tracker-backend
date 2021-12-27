package com.foodtrucktracker.foundation.models;

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
    @JoinColumn(name = "id")
    private Truck truck;
}
