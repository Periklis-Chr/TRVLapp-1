package com.iee.trvlapp.roomEntities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "Tours_table")
public class Tours {

    @PrimaryKey
    @ColumnInfo(name ="Tours_id")
    private int Tid;
    @ColumnInfo(name ="Tours_City")
    private String City;
    @ColumnInfo(name ="Tours_Country")
    private String Country;
    @ColumnInfo(name ="Tours_Duration")
    private String Duration;
    @ColumnInfo(name ="Tours_Type")
    private String Type;

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

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
