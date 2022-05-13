package com.iee.trvlapp.ui.Offices;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iee.trvlapp.MainActivity;
import com.iee.trvlapp.R;
import com.iee.trvlapp.databinding.FragmentOfficesBinding;
import com.iee.trvlapp.roomEntities.Offices;

import java.util.ArrayList;
import java.util.List;

public class OfficesFragment extends Fragment {

    private FragmentOfficesBinding binding;

//    RecyclerView recyclerView;
//    RecyclerView.Adapter adapter;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        OfficesViewModel officesViewModel =
                new ViewModelProvider(this).get(OfficesViewModel.class);

        binding = FragmentOfficesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//
//        RecyclerView recyclerView=binding.officeRecyclerview;
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setHasFixedSize(true);
//
//        final OfficeRecyclerViewAdapter adapter = new OfficeRecyclerViewAdapter();
//        recyclerView.setAdapter(adapter);
//
//        officesViewModel.getAllOffices().observe(getViewLifecycleOwner(),new Observer<List<Offices>>() {
//    @Override
//    public void onChanged(List<Offices> offices) {
//
//        adapter.setOffices(offices);
//    }
//});



          //reading room db

        List<Offices> offices = MainActivity.appDatabase.officesDao().getOffices();

        String result ="";
        for (Offices i: offices) {
            int office_id = i.getDid();
            String office_name = i.getName();
            String office_address = i.getAddress();
            result = result + "\n Id: " + office_id + "\n Name: " + office_name + "\n Surname: " + office_address + "\n";

        }

        binding.officeTextView.setText(result);



//listener for adding offices

        binding.floatingActionButtonAddOffices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_offices_to_addOfficesFragment);
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