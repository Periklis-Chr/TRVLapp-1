package com.iee.trvlapp.ui.Hotels;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import com.iee.trvlapp.R;
import com.iee.trvlapp.databinding.FragmentUpdateHotelsBinding;
import com.iee.trvlapp.roomEntities.CityHotels;

public class UpdateHotelsFragment extends Fragment {

    private FragmentUpdateHotelsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HotelsViewModel hotelsViewModel =
                new ViewModelProvider(this).get(HotelsViewModel.class);

        binding = FragmentUpdateHotelsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        // Retrieves Data passed From CityHotels Fragment

        Bundle bundle = getArguments();
        int id = bundle.getInt("id");
        String name = bundle.getString("name");
        String address = bundle.getString("address");
        int stars = bundle.getInt("stars");
        int tid = bundle.getInt("tid");

        binding.updateHotelName.setText(name);
        binding.updateHotelAddress.setText(address);
        binding.updateHotelStars.setText(String.valueOf(stars));
        binding.updateHotelTid.setText(String.valueOf(tid));


        //  //  Updates Chosen CityHotel and Navigates to CityHotel Fragment

        binding.updateHotelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = binding.updateHotelName.getText().toString();
                String address = binding.updateHotelAddress.getText().toString();
                String stars = binding.updateHotelStars.getText().toString();
                String tid = binding.updateHotelTid.getText().toString();

                if (binding.updateHotelName.length() != 0 && binding.updateHotelAddress.length() != 0 && binding.updateHotelStars.length() != 0 && binding.updateHotelTid.length() != 0) {

                    CityHotels cityHotels = new CityHotels();
                    cityHotels.setHid(id);
                    cityHotels.setHotelName(name);
                    cityHotels.setHotelAddress(address);
                    cityHotels.setHotelStars(Integer.parseInt(stars));
                    cityHotels.setTid(Integer.parseInt(tid));
                    hotelsViewModel.updateHotel(cityHotels);

                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    HotelsFragment hotelsFragment = new HotelsFragment();
                    fragmentTransaction.replace(R.id.nav_host_fragment_content_main, hotelsFragment);
                    fragmentTransaction.commit();
                }else{
                    Toast.makeText(getActivity(), "Fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Update Action is Canceled and Navigates to CityHotels Fragment

        binding.cancelUpdateHotelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                HotelsFragment hotelsFragment = new HotelsFragment();
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main, hotelsFragment);
                fragmentTransaction.commit();

            }
        });

        return root;
    }

}
