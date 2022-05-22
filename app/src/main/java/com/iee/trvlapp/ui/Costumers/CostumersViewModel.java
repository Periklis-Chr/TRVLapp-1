package com.iee.trvlapp.ui.Costumers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iee.trvlapp.FirestoreEntities.Costumers;
import com.iee.trvlapp.MainActivity;
import com.iee.trvlapp.roomEntities.CityHotels;
import com.iee.trvlapp.roomEntities.Offices;
import com.iee.trvlapp.roomEntities.Packages;
import com.iee.trvlapp.roomEntities.Tours;

import java.util.List;

public class CostumersViewModel extends ViewModel {
    private Tours tourForAdapter;
    private Packages packageForAdapter;
    private Offices officeForAdapter;

    private List<Packages> packageList;
    private List<Tours> toursList;
    private List<Offices> officesList;
    private List<CityHotels> hotelsList;
    public CostumersViewModel() {

        packageList = MainActivity.appDatabase.packagesDao().getPackagesList();


    }






    public Packages getPackageById(int id) {
        packageForAdapter = MainActivity.appDatabase.packagesDao().getPackageById(id);

        return packageForAdapter;
    }


    public Tours getTourById(int id) {
        tourForAdapter = MainActivity.appDatabase.toursDao().getTourById(id);

        return tourForAdapter;
    }


    public Offices getOfficeById(int id) {
        officeForAdapter = MainActivity.appDatabase.officesDao().getOfficeById(id);

        return officeForAdapter;
    }

    public List<Packages> getAllPackages() {
        return packageList;
    }

    public List<Tours> getAllTours() {
        return toursList;
    }

    public List<Offices> getAllOffices() {
        return officesList;
    }




    public List<CityHotels> getAllHotels() {
        return hotelsList;
    }


    public List<CityHotels> getHotelsList(int id) {

        hotelsList = MainActivity.appDatabase.cityHotelsDao().getCityHotelsByTid(id);


        return hotelsList;
    }
}
