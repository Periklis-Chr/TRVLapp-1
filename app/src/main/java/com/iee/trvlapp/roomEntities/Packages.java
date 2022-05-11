package com.iee.trvlapp.roomEntities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Packages_table")
public class Packages {

    @PrimaryKey
    @ColumnInfo(name ="Packages_id")
    private int Pid;
    @ColumnInfo(name ="Packages_Did")
    private int Did;
    @ColumnInfo(name ="Packages_Tid")
    private int Tid;
    @ColumnInfo(name ="Packages_DepartureTime")
    private int DepartureTime;
    @ColumnInfo(name ="Packages_Cost")
    private Double Cost;

    public int getPid() {
        return Pid;
    }

    public void setPid(int pid) {
        Pid = pid;
    }

    public int getDid() {
        return Did;
    }

    public void setDid(int did) {
        Did = did;
    }

    public int getTid() {
        return Tid;
    }

    public void setTid(int tid) {
        Tid = tid;
    }

    public int getDepartureTime() {
        return DepartureTime;
    }

    public void setDepartureTime(int departureTime) {
        DepartureTime = departureTime;
    }

    public Double getCost() {
        return Cost;
    }

    public void setCost(Double cost) {
        Cost = cost;
    }
}
