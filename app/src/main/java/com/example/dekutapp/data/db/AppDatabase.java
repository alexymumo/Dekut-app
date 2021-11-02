package com.example.dekutapp.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dekutapp.data.dao.UserDao;
import com.example.dekutapp.data.entity.User;

@Database(entities = {User.class}, exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    private static AppDatabase INSTANCE;

    static AppDatabase getInstance(final Context context){
        if (INSTANCE == null){
            synchronized (AppDatabase.class){
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "users"
                            ).build();

                }
            }
        }
        return INSTANCE;
    }

}
