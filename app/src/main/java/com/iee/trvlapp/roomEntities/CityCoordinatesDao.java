package com.iee.trvlapp.roomEntities;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CityCoordinatesDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    public void addCityCoordinates(CityCoordinates cityCoordinates);

    @Query("select * from CityCoordinates_table")
    public List<CityCoordinates> getCityCoordinates();

}
