package com.iee.trvlapp.ui.Packages;

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
import com.iee.trvlapp.databinding.FragmentAddPackagesBinding;

import com.iee.trvlapp.roomEntities.Packages;

public class AddPackagesFragment extends Fragment {

    private FragmentAddPackagesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PackagesViewModel packagesViewModel =
                new ViewModelProvider(this).get(PackagesViewModel.class);

        binding = FragmentAddPackagesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.packageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertPackageData();
            }
        });


        return root;
    }


    public void insertPackageData(){

        int package_id= Integer.parseInt(binding.PackageId.getText().toString());
        int package_ofid=Integer.parseInt(binding.PackageOfid.getText().toString());
        int package_tid=Integer.parseInt(binding.packageTid.getText().toString());
        int  package_departure=Integer.parseInt(binding.packageDeparture.getText().toString());
        Double package_cost=Double.parseDouble(binding.packageCost.getText().toString());

        Packages Package= new Packages();
        Package.setPid(package_id);
        Package.setDid(package_ofid);
        Package.setTid(package_tid);
        Package.setDepartureTime(package_departure);
        Package.setCost(package_cost);

        MainActivity.appDatabase.packagesDao().addPackage(Package);

        Toast.makeText(getActivity(),"Package Added Succesfully",Toast.LENGTH_LONG).show();
        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_addPackagesFragment_to_nav_packages);


    }






    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




}
