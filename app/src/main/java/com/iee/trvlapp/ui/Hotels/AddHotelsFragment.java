package com.iee.trvlapp.ui.Hotels;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.iee.trvlapp.MainActivity;
import com.iee.trvlapp.R;
import com.iee.trvlapp.databinding.FragmentAddHotelsBinding;
import com.iee.trvlapp.databinding.FragmentAddOfficesBinding;
import com.iee.trvlapp.roomEntities.CityHotels;
import com.iee.trvlapp.roomEntities.Offices;
import com.iee.trvlapp.ui.Offices.OfficesViewModel;

public class AddHotelsFragment extends Fragment {


    private FragmentAddHotelsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HotelsViewModel hotelsViewModel =
                new ViewModelProvider(this).get(HotelsViewModel.class);

        binding = FragmentAddHotelsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Calls function to handle CityHotel Insertion

        binding.addHotelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertOfficeData();
            }
        });

        return root;
    }


    ////Inserts  CityHotel
    public void insertOfficeData() {

        String name = binding.addHotelName.getText().toString();
        String address = binding.addHotelAddress.getText().toString();
        int stars = Integer.parseInt(binding.addHotelStars.getText().toString());
        int tid = Integer.parseInt(binding.addHotelTid.getText().toString());

        if (binding.addHotelName.length() != 0 && binding.addHotelAddress.length() != 0 && binding.addHotelStars.length() != 0 && binding.addHotelTid.length() != 0) {
            CityHotels cityHotels = new CityHotels();
            cityHotels.setHotelName(name);
            cityHotels.setHotelAddress(address);
            cityHotels.setHotelStars(stars);
            cityHotels.setTid(tid);

            MainActivity.appDatabase.cityHotelsDao().addCityHotel(cityHotels);

            Toast.makeText(getActivity(), "Hotel Added Succesfully", Toast.LENGTH_LONG).show();
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_addHotelsFragment_to_nav_hotels);
        } else {
            Toast.makeText(getActivity(), "Fill all fields", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
