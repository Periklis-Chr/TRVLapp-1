package com.iee.trvlapp.ui.Tours;

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
import com.iee.trvlapp.databinding.FragmentUpdateOfficesBinding;
import com.iee.trvlapp.databinding.FragmentUpdateToursBinding;
import com.iee.trvlapp.roomEntities.Offices;
import com.iee.trvlapp.roomEntities.Tours;
import com.iee.trvlapp.ui.Offices.OfficesFragment;
import com.iee.trvlapp.ui.Offices.OfficesViewModel;
import com.iee.trvlapp.ui.Offices.UpdateOfficesFragment;

public class UpdateToursFragment extends Fragment {

    private FragmentUpdateToursBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ToursViewModel toursViewModel =
                new ViewModelProvider(this).get(ToursViewModel.class);

        binding = FragmentUpdateToursBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        //listener for confirmation of data insertion

        Bundle bundle = getArguments();
        String id = bundle.getString("id");
        String city = bundle.getString("city");
        String country = bundle.getString("country");
        String duration = bundle.getString("duration");
        String type = bundle.getString("type");


        binding.updateTourId.setText(id);
        binding.updateTourCity.setText(city);
        binding.updateTourCountry.setText(country);
        binding.updateTourDuration.setText(duration);
        binding.updateTourType.setText(type);


        //updates data from Room db onClick

        binding.updateToursButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = binding.updateTourId.getText().toString();
                String city = binding.updateTourCity.getText().toString();
                String country = binding.updateTourCountry.getText().toString();
                String duration = binding.updateTourDuration.getText().toString();
                String type = binding.updateTourType.getText().toString();


                Tours tour = new Tours();
                tour.setTid(Integer.parseInt(id));
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


        // cancel update form

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
