package com.foodtrucktracker.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "menuitemphotos")
public class MenuItemPhoto extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long menuItemPhotoId;

    @ManyToOne
    @JoinColumn(name = "menuItemId")
    @JsonIgnoreProperties(value = "itemPhotos", allowSetters = true)
    private MenuItem menuItem;
}
