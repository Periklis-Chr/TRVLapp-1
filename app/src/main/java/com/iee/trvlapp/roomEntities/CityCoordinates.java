package com.iee.trvlapp.roomEntities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "CityCoordinates_table")
public class CityCoordinates {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "city_name")
    private String cityName;
    @ColumnInfo(name = "city_longitude")
    private double longitude;
    @ColumnInfo(name = "city_latitude")
    private double latitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CityCoordinates() {
    }

    public CityCoordinates(String cityName, double longitude, double latitude) {
        this.cityName = cityName;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
