package com.example.dekutapp;

public class User {
    public String  email, regno, name, course,year;

    public User(){
    }
    public User(String name, String year,String regno,String email,String course){
        this.name = name;
        this.year = year;
        this.regno = regno;
        this.email = email;
        this.course = course;

    }

}
