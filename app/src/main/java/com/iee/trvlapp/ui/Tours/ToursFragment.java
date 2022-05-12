package com.iee.trvlapp.ui.Tours;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.iee.trvlapp.MainActivity;
import com.iee.trvlapp.R;
import com.iee.trvlapp.databinding.FragmentToursBinding;
import com.iee.trvlapp.roomEntities.Offices;
import com.iee.trvlapp.roomEntities.Tours;

import java.util.List;

public class ToursFragment extends Fragment {

    private FragmentToursBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ToursViewModel toursViewModel =
                new ViewModelProvider(this).get(ToursViewModel.class);

        binding = FragmentToursBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        // reading room db

        List<Tours> tours = MainActivity.appDatabase.toursDao().getTours();

        String result ="";
        for (Tours i: tours) {
            int tours_id = i.getTid();
            String tours_city = i.getCity();
            String tours_country = i.getCountry();
            String tours_duration=i.getDuration();
                    String tours_type=i.getType();
            result = result + "\n Id: " + tours_id + "\n Name: " + tours_city+ "\n Surname: " + tours_country+ "\n duration: "+tours_duration+"\n type: "+tours_type+"\n";

        }

        binding.toursTextView.setText(result);


//listener for adding tours

binding.floatingActionButtonAddTours.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Navigation.findNavController(view).navigate(R.id.action_nav_tours_to_addToursFragment);
    }
});


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}