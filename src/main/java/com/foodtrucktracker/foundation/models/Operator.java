package com.foodtrucktracker.foundation.models;


import javax.persistence.*;


@Entity
@Table(name = "operators")
public class Operator extends User {

    public Operator(String username, String password, String email) {
        super(username, password, email);
    }

}
