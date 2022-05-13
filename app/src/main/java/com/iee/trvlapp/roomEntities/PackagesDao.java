package com.iee.trvlapp.roomEntities;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PackagesDao {
    @Insert
    public void addPackage(Packages Package);

    @Query("select * from Packages_table")
    public LiveData<List<Packages>> getPackages();

    @Delete
    public void deletePackages(Packages Package);

    @Update
    public void updatePackages(Packages Package);
}
