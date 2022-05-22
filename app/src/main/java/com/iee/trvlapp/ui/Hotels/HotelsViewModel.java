package com.iee.trvlapp.ui.Hotels;

import android.app.Application;
import android.view.View;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.iee.trvlapp.MainActivity;
import com.iee.trvlapp.roomEntities.CityHotels;
import com.iee.trvlapp.roomEntities.Offices;

import java.util.List;

public class HotelsViewModel extends AndroidViewModel {


    private LiveData<List<CityHotels>> HotelsList;
    private LiveData<List<CityHotels>> hotelsListNameASC;
    private LiveData<List<CityHotels>> hotelsListNameDESC;


    public HotelsViewModel(Application application) {
        super(application);

        HotelsList = MainActivity.appDatabase.cityHotelsDao().getCityHotels();
        hotelsListNameDESC = MainActivity.appDatabase.cityHotelsDao().getHotelsOrderedByNameDesc();
        hotelsListNameASC = MainActivity.appDatabase.cityHotelsDao().getHotelsOrderedByNameASC();
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
