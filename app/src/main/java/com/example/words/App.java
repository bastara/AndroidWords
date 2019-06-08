package com.example.words;

import android.app.Application;

import androidx.room.Room;

import com.example.words.db.DatabaseHelper;


public class App extends Application {

    private static App instance;
    private DatabaseHelper db;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        db = Room.databaseBuilder(getApplicationContext(), DatabaseHelper.class, this.getString(R.string.DB))
                 .allowMainThreadQueries()
                 .build();
    }

    public DatabaseHelper getDatabaseInstance() {
        return db;
    }
}