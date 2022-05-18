package com.iee.trvlapp.roomEntities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "offices_table")
public class Offices {
    @PrimaryKey()
    @ColumnInfo(name ="Offices_id")@NonNull
    private int Did;
    @ColumnInfo(name ="Offices_name")
    private String Name;
    @ColumnInfo(name ="Offices_address")
    private String Address;

    public Offices(){}

    public Offices(int did, String name, String address) {
        Did = did;
        Name = name;
        Address = address;
    }

    public int getDid() {
        return Did;
    }

    public void setDid(int did) {
        Did = did;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
