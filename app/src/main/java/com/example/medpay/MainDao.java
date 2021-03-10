package com.example.medpay;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MainDao {

    @Insert(onConflict = REPLACE)
    void insert (MainEntity entity);

    @Delete
    void delete (MainEntity entity);

    @Update
    void update (MainEntity entity);

    //delete all
    @Delete
    void reset(List<MainEntity> mainEntities);

    //get all
    @Query("SELECT * FROM transactions ORDER BY ID DESC")
    LiveData<List<MainEntity>> getAll();

}
