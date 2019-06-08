package com.example.words.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.words.db.model.DataModel;

@Database(entities = { DataModel.class }, version = 1, exportSchema = false)
public abstract class DatabaseHelper extends RoomDatabase {

    public abstract DataDao getDataDao();

//    @Override
//    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
//        return null;
//    }
//
//    @Override
//    protected InvalidationTracker createInvalidationTracker() {
//        return null;
//    }
}