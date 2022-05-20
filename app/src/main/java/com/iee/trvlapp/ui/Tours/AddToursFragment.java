package com.iee.trvlapp.ui.Tours;

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
import com.iee.trvlapp.databinding.FragmentAddToursBinding;
import com.iee.trvlapp.roomEntities.Tours;


public class AddToursFragment extends Fragment {

    private FragmentAddToursBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ToursViewModel toursViewModel =
                new ViewModelProvider(this).get(ToursViewModel.class);

        binding = FragmentAddToursBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //listener for confirmation of data insertion

        binding.toursButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertToursData();
            }
        });


        return root;
    }


    //insert data to db function

    public void insertToursData() {

       String tour_id =binding.TourId.getText().toString();
        String tour_city = binding.TourCity.getText().toString();
        String tour_country = binding.TourCountry.getText().toString();
        String tour_duration =binding.tourDuration.getText().toString();
        String tour_type = binding.tourType.getText().toString();


        if (binding.TourId.length() != 0 && binding.TourCity.length() != 0 && binding.TourCountry.length() != 0 && binding.tourDuration.length() != 0 && binding.tourType.length() != 0) {


            Tours tour = new Tours();
            tour.setTid(Integer.parseInt(tour_id));
            tour.setCity(tour_city);
            tour.setCountry(tour_country);
            tour.setDuration( Integer.parseInt(tour_duration));
            tour.setType(tour_type);

            MainActivity.appDatabase.toursDao().addTour(tour);

            Toast.makeText(getActivity(), "Tour Added Successfully", Toast.LENGTH_LONG).show();
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_addToursFragment_to_nav_tours);
        }else{
            Toast.makeText(getActivity(), "Fill al fields", Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}
