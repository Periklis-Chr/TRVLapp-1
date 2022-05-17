package com.iee.trvlapp.ui.Offices;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.iee.trvlapp.MainActivity;
import com.iee.trvlapp.roomEntities.AppDatabase;
import com.iee.trvlapp.roomEntities.Offices;
import com.iee.trvlapp.roomEntities.Packages;

import java.util.List;

public class OfficesViewModel extends AndroidViewModel {


    private LiveData<List<Offices>> officesList;
    private LiveData<List<Offices>> officesListNameASC;
    private LiveData<List<Offices>> officesListNameDESC;


    public OfficesViewModel(Application application) {
        super(application);

        officesList = MainActivity.appDatabase.officesDao().getOffices();
        officesListNameDESC = MainActivity.appDatabase.officesDao().getOfficesOrderedByNameDesc();
        officesListNameASC = MainActivity.appDatabase.officesDao().getOfficesOrderedByNameASC();
    }

    public LiveData<List<Offices>> getAllOffices() {
        return officesList;
    }

    public void deleteOffice(Offices office) {
        MainActivity.appDatabase.officesDao().deleteOffices(office);
    }

    public void updateOffice(Offices office) {
        MainActivity.appDatabase.officesDao().updateOffices(office);
    }

    public LiveData<List<Offices>> getofficesOrderByNameDesc() {
        return officesListNameDESC;
    }

    public LiveData<List<Offices>> getofficesOrderByNameAsc() {
        return officesListNameASC;
    }

    public void deleteAll() {
        MainActivity.appDatabase.officesDao().deleteAllOffices();
    }
}