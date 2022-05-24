package com.iee.trvlapp.ui.Tours;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.iee.trvlapp.R;
import com.iee.trvlapp.databinding.FragmentUpdateToursBinding;
import com.iee.trvlapp.roomEntities.DataConverter;
import com.iee.trvlapp.roomEntities.Tours;
import com.iee.trvlapp.ui.Offices.OfficesFragment;

import java.io.FileNotFoundException;
import java.io.IOException;


public class UpdateToursFragment extends Fragment {

    private FragmentUpdateToursBinding binding;
    Bitmap bitmap = null;
    final int SELECT_PHOTO = 1;
    Uri uri;
    boolean flag = false;

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
        byte[] image = bundle.getByteArray("image");
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
                String duration = binding.updateTourDuration.getText().toString();
                String type = binding.updateTourType.getText().toString();


                if (binding.updateTourCity.length() != 0 && binding.updateTourCountry.length() != 0 && binding.updateTourDuration.length() != 0 && binding.updateTourType.length() != 0) {

                    Tours tour = new Tours();
                    tour.setTid(id);
                    tour.setCity(city);
                    tour.setCountry(country);
                    tour.setDuration(Integer.parseInt(duration));
                    tour.setType(type);
                    if (flag) {
                        tour.setImageTour(DataConverter.convertIMage2ByteArray(bitmap));
                    } else {
                        tour.setImageTour(image);
                    }
                    toursViewModel.updateTour(tour);

                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    ToursFragment toursFragment = new ToursFragment();
                    fragmentTransaction.replace(R.id.nav_host_fragment_content_main, toursFragment);
                    fragmentTransaction.commit();
                } else {
                    Toast.makeText(getActivity(), "Fill all the fields", Toast.LENGTH_SHORT).show();
                }
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

        //Image Selection onClick

        binding.updatebrowseImageLibraryTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImage(view);
            }
        });


        return root;
    }

    //Makes Intent to handle Image selection

    public void pickImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, SELECT_PHOTO);
    }

    //Opens image selector and gets the image uri

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == SELECT_PHOTO && data != null && data.getData() != null) {
            uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(), uri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        flag=true;
    }

}
