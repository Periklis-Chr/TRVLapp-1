package com.iee.trvlapp.ui.Packages;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iee.trvlapp.MainActivity;
import com.iee.trvlapp.roomEntities.Offices;
import com.iee.trvlapp.roomEntities.Packages;

import java.util.List;

public class PackagesViewModel extends ViewModel {

    private LiveData<List<Packages>> packageList;
    private LiveData<List<Packages>> packagesListNameASC;
    private LiveData<List<Packages>> packagesListNameDESC;

    public PackagesViewModel() {
        packageList = MainActivity.appDatabase.packagesDao().getPackages();

        packagesListNameDESC = MainActivity.appDatabase.packagesDao().getPackagesOrderedByNameDesc();
        packagesListNameASC = MainActivity.appDatabase.packagesDao().getPackagesOrderedByNameASC();


    }

    public LiveData<List<Packages>> getAllPackages() {
        return packageList;
    }


    public void deletePackage(Packages packages) {
        MainActivity.appDatabase.packagesDao().deletePackages(packages);
    }

    public void updatePackage(Packages packages) {
        MainActivity.appDatabase.packagesDao().deletePackages(packages);
    }

    public LiveData<List<Packages>> getPackagesOrderByNameDesc() {
        return packagesListNameDESC;
    }

    public LiveData<List<Packages>> getPackagesOrderByNameAsc() {
        return packagesListNameASC;
    }

}

