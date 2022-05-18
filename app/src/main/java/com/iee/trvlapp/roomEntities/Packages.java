package com.iee.trvlapp.roomEntities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Packages_table",
        foreignKeys = {@ForeignKey(entity = Offices.class,
                parentColumns = "Offices_id",
                childColumns = "Packages_Did",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE),
                @ForeignKey(entity = Tours.class,
                        parentColumns = "Tours_id",
                        childColumns = "Packages_Tid",
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE)})


public class Packages {

    @PrimaryKey
    @ColumnInfo(name = "Packages_id")
    @NonNull
    private int Pid;
    @ColumnInfo(name = "Packages_Did")
    @NonNull
    private int Did;
    @ColumnInfo(name = "Packages_Tid")
    @NonNull
    private int Tid;
    @ColumnInfo(name = "Packages_DepartureTime")
    private int DepartureTime;
    @ColumnInfo(name = "Packages_Cost")
    private Double Cost;


    public Packages() {
    }

    public Packages(int pid, int did, int tid, int departureTime, Double cost) {
        Pid = pid;
        Did = did;
        Tid = tid;
        DepartureTime = departureTime;
        Cost = cost;
    }

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
