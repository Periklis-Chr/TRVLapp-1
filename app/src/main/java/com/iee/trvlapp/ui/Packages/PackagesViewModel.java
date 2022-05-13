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

    public PackagesViewModel() {
        packageList = MainActivity.appDatabase.packagesDao().getPackages();
    }

    public LiveData<List<Packages>> getAllPackages() {
        return packageList;
    }

}

