package com.iee.trvlapp.ui.Hotels;

import android.app.Application;
import android.view.View;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.iee.trvlapp.MainActivity;
import com.iee.trvlapp.roomEntities.CityHotels;
import com.iee.trvlapp.roomEntities.Offices;
import com.iee.trvlapp.roomEntities.Tours;

import java.util.List;

public class HotelsViewModel extends AndroidViewModel {


    private LiveData<List<CityHotels>> HotelsList;
    private LiveData<List<CityHotels>> hotelsListNameASC;
    private LiveData<List<CityHotels>> hotelsListNameDESC;
    private List<Tours> toursList;

    public HotelsViewModel(Application application) {
        super(application);
        toursList = MainActivity.appDatabase.toursDao().getToursList();
        HotelsList = MainActivity.appDatabase.cityHotelsDao().getCityHotels();
        hotelsListNameDESC = MainActivity.appDatabase.cityHotelsDao().getHotelsOrderedByNameDesc();
        hotelsListNameASC = MainActivity.appDatabase.cityHotelsDao().getHotelsOrderedByNameASC();
    }

    // CityHotels Dao methods implementation for Hotels Fragment

    public List<Tours> getAllTours() {
        return toursList;
    }


    public LiveData<List<CityHotels>> getAllHotels() {
        return HotelsList;
    }

    public void deleteHotel(CityHotels hotel) {
        MainActivity.appDatabase.cityHotelsDao().deleteCityHotels(hotel);
    }

    public void updateHotel(CityHotels hotels) {
        MainActivity.appDatabase.cityHotelsDao().updateCityHotel(hotels);
    }

    public LiveData<List<CityHotels>> getHotelsOrderByNameDesc() {
        return hotelsListNameDESC;
    }

    public LiveData<List<CityHotels>> getHotelsOrderByNameAsc() {
        return hotelsListNameASC;
    }

}
