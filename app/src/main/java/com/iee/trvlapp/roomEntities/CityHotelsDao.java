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

    @Query("select * from CityHotels_table")
    public List<CityHotels> getCityHotelsList();

    @Query("select * from CityHotels_table where Hotels_tid=:id")
    public List<CityHotels> getCityHotelsByTid(int id);



    @Query("select * from CityHotels_table order by Hotels_name DESC")
    public LiveData<List<CityHotels>> getHotelsOrderedByNameDesc();


    @Query("select * from CityHotels_table order by Hotels_name ASC")
    public LiveData<List<CityHotels>> getHotelsOrderedByNameASC();




    @Delete()
    public void deleteCityHotels(CityHotels cityHotels);

    @Update(onConflict = OnConflictStrategy.ABORT)
    public void updateCityHotel(CityHotels cityHotels);

    @Query("DELETE FROM CityHotels_table where 1=1")
    public void deleteAllCityHotels();



    @Query("select * from CityHotels_table  where Hotels_id=:id")
    public CityHotels getCityHotelById(int id);



}
