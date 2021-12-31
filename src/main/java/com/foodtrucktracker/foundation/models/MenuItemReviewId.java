package com.foodtrucktracker.foundation.models;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MenuItemReviewId implements Serializable {
    private long diner;
    private long menuItem;

    public MenuItemReviewId() {
    }

    public long getDiner() {
        return diner;
    }

    public void setDiner(long diner) {
        this.diner = diner;
    }

    public long getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(long menuItem) {
        this.menuItem = menuItem;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        MenuItemReviewId that = (MenuItemReviewId) o;
        return diner == that.diner &&
                menuItem == that.menuItem;
    }

    @Override
    public int hashCode()
    {
        return 37;
    }
}
