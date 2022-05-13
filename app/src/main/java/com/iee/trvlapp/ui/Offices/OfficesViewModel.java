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

import java.util.List;

public class OfficesViewModel extends AndroidViewModel {

//    private final MutableLiveData<String> mText;
private LiveData<List<Offices>> officesList;


    public OfficesViewModel(Application application) {
        super(application);
//        mText = new MutableLiveData<>();
//        mText.setValue("This is Offices fragment");

//        officesList= MainActivity.appDatabase.officesDao().getOffices();
    }

public  LiveData<List<Offices>> getAllOffices(){
        return officesList;
    }



//    public LiveData<String> getText() {
//        return mText;
//    }


}