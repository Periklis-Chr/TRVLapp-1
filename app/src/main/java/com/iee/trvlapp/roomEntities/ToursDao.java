package com.iee.trvlapp.roomEntities;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface ToursDao {
    @Insert
    public void addTour(Tours tour);

    @Query("select * from Tours_table")
    public List<Tours> getTours();

    @Delete
    public void deleteTours(Tours tour);

    @Update
    public void updateTours(Tours tour);
}
