package com.example.dekutapp.data.models;

import androidx.room.Entity;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

@Entity(tableName = "users")
public class User implements Serializable {
    public static String keyTableName;
    public String  email, regno, name, course,year;

    @Exclude
    public boolean isAuthenticated;

    public User(){

    }
    public User(String name, String year,String regno,String email,String course, boolean isAuthenticated){
        this.name = name;
        this.year = year;
        this.regno = regno;
        this.email = email;
        this.course = course;
        this.isAuthenticated = isAuthenticated;
    }
    //add setter and getters if possible
}
