package com.example.dekutapp.data.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "news")
public class News {
    private String title, body;

    @PrimaryKey(autoGenerate = true)
    private Integer id;
    public News(String title, String body){
        this.title = title;
        this.body = body;
    }
    @NonNull
    public String getBody() {
        return this.body;
    }
    @NonNull
    public String getTitle() {
        return this.title;
    }
    public void setBody(@NonNull String body) {
        this.body = body;
    }
    public void setTitle(@NonNull String title) {
        this.title = title;
    }
}
