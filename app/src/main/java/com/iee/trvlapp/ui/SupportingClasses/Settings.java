package com.iee.trvlapp.ui.SupportingClasses;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.iee.trvlapp.databinding.FragmentSettingsBinding;

public class Settings extends Fragment {

    private FragmentSettingsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        //Listener Handlers for Database Actions regarding ImportData Class ( Prepopulate, Delete )

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.homeFillData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImportData.fillRoomDatabase();
            }
        });

        binding.homedeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImportData.deleteRoomData();
            }
        });


        binding.homeFillDatafs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImportData.fillFirestoreDatabase();
            }
        });


        binding.homedeletefsData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImportData.delteAllFirestoreData();
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
