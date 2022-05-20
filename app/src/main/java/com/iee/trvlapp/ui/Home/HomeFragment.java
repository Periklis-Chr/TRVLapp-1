package com.iee.trvlapp.ui.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.iee.trvlapp.MainActivity;
import com.iee.trvlapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


//        binding.homeFillData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                MainActivity.fillRoomDatabase();
//            }
//        });
//
//binding.deleteAllData.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//        MainActivity.appDatabase.officesDao().deleteAllOffices();
//        MainActivity.appDatabase.toursDao().deleteAllTours();
//        MainActivity.appDatabase.packagesDao().deleteAllPackages();
//        MainActivity.appDatabase.cityHotelsDao().deleteAllCityHotels();
//    }
//});
//
//
//
//binding.homeFillDataFs.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//        MainActivity.fillFirestoreDatabase();
//    }
//});
//
//
//binding.deleteAllDataFs.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//        MainActivity.delteAllFirestoreData();
//    }
//});






        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}