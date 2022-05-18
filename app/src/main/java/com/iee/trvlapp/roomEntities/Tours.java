package com.iee.trvlapp.roomEntities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "Tours_table")
public class Tours {

    @PrimaryKey
    @ColumnInfo(name ="Tours_id")@NonNull
    private int Tid;
    @ColumnInfo(name ="Tours_City")
    private String City;
    @ColumnInfo(name ="Tours_Country")
    private String Country;
    @ColumnInfo(name ="Tours_Duration")
    private int Duration;
    @ColumnInfo(name ="Tours_Type")
    private String Type;


    public Tours(){}
    public Tours(int tid, String city, String country, int duration, String type) {
        Tid = tid;
        City = city;
        Country = country;
        Duration = duration;
        Type = type;
    }

    public int getTid() {
        return Tid;
    }

    public void setTid(int tid) {
        Tid = tid;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int duration) {
        Duration = duration;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
