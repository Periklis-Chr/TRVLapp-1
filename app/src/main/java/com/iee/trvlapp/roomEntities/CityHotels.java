package com.iee.trvlapp.roomEntities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "CityHotels_table")
public class CityHotels {

    @PrimaryKey()
    @ColumnInfo(name = "Hotels_id")
    private int hid;
    @ColumnInfo(name = "Hotels_name")
    private String hotelName;
    @ColumnInfo(name = "Hotels_address")
    private String hotelAddress;
    @ColumnInfo(name = "Hotels_stars")
    private int hotelStars;
    @ColumnInfo(name = "Hotels_tid")
    private int tid;


    public CityHotels() {
    }

    public CityHotels(int hid, String hotelName, String hotelAddress, int hotelStars, int tid) {
        this.hid = hid;
        this.hotelName = hotelName;
        this.hotelAddress = hotelAddress;
        this.hotelStars = hotelStars;
        this.tid = tid;
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public int getHotelStars() {
        return hotelStars;
    }

    public void setHotelStars(int hotelStars) {
        this.hotelStars = hotelStars;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }
}
