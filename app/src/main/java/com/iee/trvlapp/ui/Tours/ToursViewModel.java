package com.iee.trvlapp.ui.Tours;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iee.trvlapp.MainActivity;
import com.iee.trvlapp.roomEntities.Offices;
import com.iee.trvlapp.roomEntities.Tours;

import java.util.List;

public class ToursViewModel extends ViewModel {

    private LiveData<List<Tours>> toursList;

    public ToursViewModel() {
        toursList = MainActivity.appDatabase.toursDao().getTours();
    }

    public LiveData<List<Tours>> getAllTours() {
        return toursList;
    }


    public void deleteTour(Tours tour) {
        MainActivity.appDatabase.toursDao().deleteTours(tour);
    }

    public void updateTour(Tours tour) {
        MainActivity.appDatabase.toursDao().updateTours(tour);
    }


}