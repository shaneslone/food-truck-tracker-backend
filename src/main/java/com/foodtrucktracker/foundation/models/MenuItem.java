package com.foodtrucktracker.foundation.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "menuItems")
public class MenuItem
    extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id")
    private Truck truck;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private String itemDescription;

    @Column(nullable = false)
    private int itemPrice;

    private List<String> itemPhotos;

    private List<Integer> customerRatings;

    private int customerRatingsAvg;
}
