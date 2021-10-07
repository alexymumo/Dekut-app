package com.example.dekutapp.model;



public class User {
   // public String uid;
    String uid, email, regno, name, course,year;

    public User(String reg_text, String email_text, String password_text, String course_text, String name_text){

    }
    public User(String uid, String name, String year,String regno,String email,String course){
        this.name = name;
        this.year = year;
        this.regno = regno;
        this.email = email;
        this.course = course;
        this.uid = uid;
    }
}
