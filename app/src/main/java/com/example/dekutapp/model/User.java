package com.example.dekutapp.model;



public class User {
   // public String uid;
    private String uid, email, regno, name, course,year;

    public User(){

    }
    public User(String uid, String name, String year, String regno, String email){
        this.name = name;
        this.year = year;
        this.regno = regno;
        this.email = email;
        this.course = course;
        this.uid = uid;
    }

    public String getCourse() {
        return course;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getRegno() {
        return regno;
    }

    public String getUid() {
        return uid;
    }

    public String getYear() {
        return year;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
