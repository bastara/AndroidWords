package com.example.words.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.words.db.model.DataModel;

import java.util.List;

@Dao
public interface DataDao {
    @Insert
    void insertWord(DataModel dataModel);

    @Delete
    void delete(DataModel dataModel);


    @Query("DELETE FROM DataModel")
    void nukeTable();

    @Query("SELECT * FROM DataModel WHERE word LIKE :word")
    DataModel getWords(String word);

    @Query("SELECT * FROM DataModel")
    List<DataModel> getAllWords();
}
