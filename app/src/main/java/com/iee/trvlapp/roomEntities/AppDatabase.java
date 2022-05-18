package com.iee.trvlapp.roomEntities;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Offices.class, Tours.class, Packages.class, CityCoordinates.class,CityHotels.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract OfficesDao officesDao();

    public abstract ToursDao toursDao();

    public abstract PackagesDao packagesDao();

    public abstract CityCoordinatesDao cityCoordinatesDao();

    public abstract CityHotelsDao cityHotelsDao();

}
