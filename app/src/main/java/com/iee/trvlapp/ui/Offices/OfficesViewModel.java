package com.iee.trvlapp.ui.Offices;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.iee.trvlapp.MainActivity;
import com.iee.trvlapp.roomEntities.Offices;

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


    //Offices Dao methods implementation for Offices Fragment

    public LiveData<List<Offices>> getAllOffices() {
        return officesList;
    }

    public void deleteOffice(Offices office) {
        MainActivity.appDatabase.officesDao().deleteOffices(office);
    }

    public void deleteAll() {
        MainActivity.appDatabase.officesDao().deleteAllOffices();
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


}