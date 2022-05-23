package com.iee.trvlapp.ui.Tours;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import com.iee.trvlapp.R;
import com.iee.trvlapp.databinding.FragmentUpdateToursBinding;
import com.iee.trvlapp.roomEntities.Tours;
import com.iee.trvlapp.ui.Offices.OfficesFragment;


public class UpdateToursFragment extends Fragment {

    private FragmentUpdateToursBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ToursViewModel toursViewModel =
                new ViewModelProvider(this).get(ToursViewModel.class);

        binding = FragmentUpdateToursBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Retrieves Data passed From Tour Fragment

        Bundle bundle = getArguments();
        int id = bundle.getInt("id");
        String city = bundle.getString("city");
        String country = bundle.getString("country");
        int duration = bundle.getInt("duration");
        String type = bundle.getString("type");

        binding.updateTourCity.setText(city);
        binding.updateTourCountry.setText(country);
        binding.updateTourDuration.setText(String.valueOf(duration));
        binding.updateTourType.setText(type);

        //  Updates Chosen Tour and Navigates to Tour Fragment

        binding.updateToursButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = binding.updateTourCity.getText().toString();
                String country = binding.updateTourCountry.getText().toString();
                int duration = Integer.parseInt(binding.updateTourDuration.getText().toString());
                String type = binding.updateTourType.getText().toString();


                Tours tour = new Tours();
                tour.setTid(id);
                tour.setCity(city);
                tour.setCountry(country);
                tour.setDuration(duration);
                tour.setType(type);
                toursViewModel.updateTour(tour);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ToursFragment toursFragment = new ToursFragment();
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main, toursFragment);
                fragmentTransaction.commit();

            }
        });


        // Update Action is Canceled and Navigates to Tour Fragment

        binding.cancelToursButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                OfficesFragment officesFragment = new OfficesFragment();
                ToursFragment toursFragment = new ToursFragment();
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main, toursFragment);
                fragmentTransaction.commit();

            }
        });

        return root;
    }

}
