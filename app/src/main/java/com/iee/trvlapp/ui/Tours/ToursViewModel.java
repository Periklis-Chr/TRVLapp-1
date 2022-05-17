package com.iee.trvlapp.ui.Tours;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iee.trvlapp.MainActivity;
import com.iee.trvlapp.roomEntities.Offices;
import com.iee.trvlapp.roomEntities.Packages;
import com.iee.trvlapp.roomEntities.Tours;

import java.util.List;

public class ToursViewModel extends ViewModel {

    private LiveData<List<Tours>> toursList;
    private LiveData<List<Tours>> toursListNameASC;
    private LiveData<List<Tours>> toursListNameDESC;

    public ToursViewModel() {
        toursList = MainActivity.appDatabase.toursDao().getTours();
        toursListNameDESC = MainActivity.appDatabase.toursDao().getToursOrderedByNameDesc();
        toursListNameASC = MainActivity.appDatabase.toursDao().getToursOrderedByNameASC();
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

    public LiveData<List<Tours>> getToursOrderByNameDesc() {
        return toursListNameDESC;
    }

    public LiveData<List<Tours>> getToursOrderByNameAsc() {
        return toursListNameASC;
    }
}