package com.iee.trvlapp.ui.SupportingClasses;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.iee.trvlapp.MainActivity;
import com.iee.trvlapp.R;
import com.iee.trvlapp.databinding.FragmentSettingsBinding;
import com.iee.trvlapp.ui.Hotels.HotelsFragment;

import java.util.Locale;


public class Settings extends Fragment {

    private FragmentSettingsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

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
