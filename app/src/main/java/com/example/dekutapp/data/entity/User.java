package com.example.dekutapp.data.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "regno")
    private String regno;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "course")
    private String course;

    @ColumnInfo(name = "year")
    private String year;
    public User(){

    }
    public User(int uid, String name, String year, String regno,String course, String email){
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

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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



}
