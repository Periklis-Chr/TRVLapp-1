package com.iee.trvlapp.ui.Offices;

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
import com.iee.trvlapp.databinding.FragmentAddOfficesBinding;
import com.iee.trvlapp.roomEntities.Offices;

public class AddOfficesFragment extends Fragment {

    private FragmentAddOfficesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        OfficesViewModel officesViewModel =
                new ViewModelProvider(this).get(OfficesViewModel.class);

        binding = FragmentAddOfficesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Calls function to handle Office Insertion

        binding.officeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertOfficeData();
            }
        });


        // Add Action is Canceled and Navigates to Offices Fragment

        binding.cancelUpdateOfficesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_addOfficesFragment_to_nav_offices);
            }
        });


        return root;
    }


    //Inserts  Office
    public void insertOfficeData() {

        String office_name = binding.OfficeName.getText().toString();
        String office_address = binding.officeAddress.getText().toString();

        if (binding.OfficeName.length() != 0 && binding.officeAddress.length() != 0) {
            Offices office = new Offices();
            office.setName(office_name);
            office.setAddress(office_address);

            MainActivity.appDatabase.officesDao().addOffice(office);

            Toast.makeText(getActivity(), "Office Added Succesfully", Toast.LENGTH_LONG).show();
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_addOfficesFragment_to_nav_offices);
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
