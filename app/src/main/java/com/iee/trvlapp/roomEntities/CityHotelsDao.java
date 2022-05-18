package com.iee.trvlapp.roomEntities;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface CityHotelsDao {


    @Insert(onConflict = OnConflictStrategy.ABORT)
    public void addCityHotel(CityHotels cityHotels);

    @Query("select * from CityHotels_table")
    public LiveData<List<CityHotels>> getCityHotels();

    @Delete()
    public void deleteCityHotels_table(CityHotels cityHotels);

    @Update(onConflict = OnConflictStrategy.ABORT)
    public void updateCityHotel(CityHotels cityHotels);
}
