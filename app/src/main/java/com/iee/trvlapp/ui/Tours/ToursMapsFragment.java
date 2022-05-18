package com.iee.trvlapp.ui.Tours;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.iee.trvlapp.MainActivity;
import com.iee.trvlapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.iee.trvlapp.ObjectEntities.CityCoordinates;


import java.util.ArrayList;
import java.util.List;

public class ToursMapsFragment extends Fragment {

    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        List<CityCoordinates> cityCoordinatesList;

        @Override
        public void onMapReady(GoogleMap googleMap) {
            CityCoordinates array[] = initCityCoordinates();

            Bundle bundle = getArguments();
            String id = bundle.getString("id");
            Toast.makeText(getActivity(), id, Toast.LENGTH_SHORT).show();

            CityCoordinates temp = new CityCoordinates();
            for (int i = 0; i < array.length; i++) {

                if (array[i].getCityNAme().equals(id) && array[i] != null) {
                    temp = array[i];
                }

            }

            LatLng latLng = new LatLng(temp.getLatitude(), temp.getLongitude());
            googleMap.addMarker(new MarkerOptions().position(latLng).title("Marker in "));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));


        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

//        initCityCoordinates();
        View view = inflater.inflate(R.layout.fragment_maps, container, false);


        return view;
    }


    //prestored City location Coordinates

    public CityCoordinates[] initCityCoordinates() {
        CityCoordinates London = new CityCoordinates("London", -0.117083, 51.513220);
        CityCoordinates Paris = new CityCoordinates("RGRG", -43, 50);
        CityCoordinates Barcelona = new CityCoordinates("DFG", -43, 50);
        CityCoordinates Berlin = new CityCoordinates("DG", -43, 50);
        CityCoordinates Rome = new CityCoordinates("DG", -43, 50);


        CityCoordinates[] array = new CityCoordinates[5];
        array[0] = London;
        array[1] = Barcelona;
        array[2] = Berlin;
        array[3] = Rome;
        array[4] = Paris;
        return array;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}