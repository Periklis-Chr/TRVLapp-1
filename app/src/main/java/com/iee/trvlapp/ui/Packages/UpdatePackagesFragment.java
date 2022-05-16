package com.iee.trvlapp.ui.Packages;

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
import com.iee.trvlapp.databinding.FragmentUpdatePackagesBinding;
import com.iee.trvlapp.roomEntities.Offices;
import com.iee.trvlapp.roomEntities.Packages;
import com.iee.trvlapp.ui.Offices.OfficesFragment;
import com.iee.trvlapp.ui.Offices.OfficesViewModel;
import com.iee.trvlapp.ui.Offices.UpdateOfficesFragment;

public class UpdatePackagesFragment extends Fragment {

    private FragmentUpdatePackagesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PackagesViewModel packagesViewModel =
                new ViewModelProvider(this).get(PackagesViewModel.class);

        binding = FragmentUpdatePackagesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        //listener for confirmation of data insertion
        Bundle bundle = getArguments();
        String id = bundle.getString("id");
        String ofid = bundle.getString("name");
        String tid = bundle.getString("address");
        String departure = bundle.getString("name");
        String cost = bundle.getString("address");


        binding.updatePackageId.setText(id);
        binding.updatePackageOfid.setText(ofid);
        binding.updatePackageTid.setText(tid);
        binding.updatePackageDeparture.setText(departure);
        binding.updatePackageCost.setText(cost);



        binding.updatePackageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = binding.updatePackageId.getText().toString();
                String ofid = binding.updatePackageOfid.getText().toString();
                String  tid= binding.updatePackageTid.getText().toString();
                String departure = binding.updatePackageDeparture.getText().toString();
                Double cost = Double.parseDouble(binding.updatePackageCost.getText().toString());



               Packages packages=new Packages();
                packages.setPid(Integer.parseInt(id));
                packages.setDid(Integer.parseInt(ofid));
                packages.setTid(Integer.parseInt(tid));
                packages.setCost(cost);
                packagesViewModel.updatePackage(packages);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                OfficesFragment officesFragment = new OfficesFragment();
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main, officesFragment);
                fragmentTransaction.commit();

            }
        });

        binding.cancelUpdatePackageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                PackagesFragment packagesFragment = new PackagesFragment();
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main, packagesFragment);
                fragmentTransaction.commit();

            }
        });


        return root;
    }
}
