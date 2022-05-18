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
public interface PackagesDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    public void addPackage(Packages Package);

    @Query("select * from Packages_table")
    public LiveData<List<Packages>> getPackages();

    @Delete
    public void deletePackages(Packages Package);

    @Update(onConflict = OnConflictStrategy.ABORT)
    public void updatePackages(Packages Package);


    @Query("select * from Packages_table order by Packages_Cost DESC")
    public LiveData<List<Packages>> getPackagesOrderedByNameDesc();


    @Query("select * from Packages_table order by Packages_Cost ASC")
    public LiveData<List<Packages>> getPackagesOrderedByNameASC();



}
