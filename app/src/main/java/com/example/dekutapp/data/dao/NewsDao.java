package com.example.dekutapp.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.dekutapp.data.models.News;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(News news);

    @Query("SELECT *FROM news ORDER BY news DESC")
    List<News> getLatestNews();
}
