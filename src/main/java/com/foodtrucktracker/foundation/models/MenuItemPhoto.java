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

    private String url;

    public MenuItemPhoto() {
    }

    public MenuItemPhoto(MenuItem menuItem, String url) {
        this.menuItem = menuItem;
        this.url = url;
    }

    public long getMenuItemPhotoId() {
        return menuItemPhotoId;
    }

    public void setMenuItemPhotoId(long menuItemPhotoId) {
        this.menuItemPhotoId = menuItemPhotoId;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
