package com.iee.trvlapp.ui.Packages;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iee.trvlapp.MainActivity;
import com.iee.trvlapp.roomEntities.Offices;
import com.iee.trvlapp.roomEntities.Packages;
import com.iee.trvlapp.roomEntities.Tours;

import java.util.List;

public class PackagesViewModel extends ViewModel {

    private LiveData<List<Packages>> packageList;
    private LiveData<List<Packages>> packagesListNameASC;
    private LiveData<List<Packages>> packagesListNameDESC;


    private List<Tours> toursList;
    private List<Offices> officesList;
    private Tours tourForAdapter;
    private Packages packageForAdapter;
private Offices officeForAdapter;
    public PackagesViewModel() {
        packageList = MainActivity.appDatabase.packagesDao().getPackages();
        packagesListNameDESC = MainActivity.appDatabase.packagesDao().getPackagesOrderedByNameDesc();
        packagesListNameASC = MainActivity.appDatabase.packagesDao().getPackagesOrderedByNameASC();

        toursList = MainActivity.appDatabase.toursDao().getToursList();
        officesList = MainActivity.appDatabase.officesDao().getOfficesList();

    }

    public LiveData<List<Packages>> getAllPackages() {
        return packageList;
    }

    public List<Tours> getAllTours() {
        return toursList;
    }

    public List<Offices> getAllOffices() {
        return officesList;
    }


    public void deletePackage(Packages packages) {
        MainActivity.appDatabase.packagesDao().deletePackages(packages);
    }

    public void updatePackage(Packages packages) {
        MainActivity.appDatabase.packagesDao().updatePackages(packages);
    }

    public LiveData<List<Packages>> getPackagesOrderByNameDesc() {
        return packagesListNameDESC;
    }

    public LiveData<List<Packages>> getPackagesOrderByNameAsc() {
        return packagesListNameASC;
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

}

