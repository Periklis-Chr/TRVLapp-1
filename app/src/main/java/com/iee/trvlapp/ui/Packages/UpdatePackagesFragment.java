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
        int id = bundle.getInt("id");
        int ofid = bundle.getInt("ofid");
        int tid = bundle.getInt("tid");
        int departure = bundle.getInt("departure");
        double cost = bundle.getDouble("cost");


        binding.updatePackageId.setText(String.valueOf(id));
        binding.updatePackageOfid.setText(String.valueOf(ofid));
        binding.updatePackageTid.setText(String.valueOf(tid));
        binding.updatePackageDeparture.setText(String.valueOf(departure));
        binding.updatePackageCost.setText(String.valueOf(cost));


        //onclick listener for updating Room data

        binding.updatePackageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(binding.updatePackageId.getText().toString());
                int ofid = Integer.parseInt(binding.updatePackageOfid.getText().toString());
                int tid = Integer.parseInt(binding.updatePackageTid.getText().toString());
                int departure = Integer.parseInt(binding.updatePackageDeparture.getText().toString());
                Double cost = Double.parseDouble(binding.updatePackageCost.getText().toString());

                Packages packages = new Packages();
                packages.setPid(id);
                packages.setDid(ofid);
                packages.setTid(tid);
                packages.setCost(cost);
                packagesViewModel.updatePackage(packages);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                OfficesFragment officesFragment = new OfficesFragment();
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main, officesFragment);
                fragmentTransaction.commit();

            }
        });


        //cancel update data

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
