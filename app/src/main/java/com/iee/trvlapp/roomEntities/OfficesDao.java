package com.iee.trvlapp.roomEntities;

import android.net.wifi.aware.PublishConfig;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface OfficesDao {
    @Insert
    public void addOffice(Offices office);

    @Query("select * from offices_table")
    public LiveData<List<Offices>> getOffices();

    @Delete
    public void deleteOffices(Offices office);

    @Update
    public void updateOffices(Offices office);



}
