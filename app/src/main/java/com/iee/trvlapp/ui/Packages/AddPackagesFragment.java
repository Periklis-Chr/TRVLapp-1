package com.iee.trvlapp.ui.Packages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.iee.trvlapp.MainActivity;
import com.iee.trvlapp.R;
import com.iee.trvlapp.databinding.FragmentAddPackagesBinding;

import com.iee.trvlapp.roomEntities.Offices;
import com.iee.trvlapp.roomEntities.Packages;
import com.iee.trvlapp.roomEntities.Tours;

import java.util.List;

public class AddPackagesFragment extends Fragment {

    private FragmentAddPackagesBinding binding;

    List<Tours> toursList;
    List<Offices> officesList;


    AutoCompleteTextView autocompleteText;
    AutoCompleteTextView autocompleteOfficeText;

    ArrayAdapter<String> adapterItems;

    ArrayAdapter<String> adapterOfficeItems;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PackagesViewModel packagesViewModel =
                new ViewModelProvider(this).get(PackagesViewModel.class);

        binding = FragmentAddPackagesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //listener for confirmation of data insertion

        binding.packageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertPackageData();
            }
        });

//////

        int i = 0;

        toursList = packagesViewModel.getAllTours();
        String[] items = new String[toursList.size()];
        int[] itemsId = new int[toursList.size()];
        for (Tours tour : toursList
        ) {
            items[i] = String.valueOf(tour.getTid()) + " " + tour.getCity();
            i++;
        }


        autocompleteText = binding.getRoot().findViewById(R.id.auto_complete_ptid);
        adapterItems = new ArrayAdapter<String>(getActivity(), R.layout.package_tours_list_item, items);

        autocompleteText.setAdapter(adapterItems);

        autocompleteText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
            }
        });


        /////


        int j = 0;

        officesList = packagesViewModel.getAllOffices();
        String[] officeItems = new String[officesList.size()];
        for (Offices office : officesList
        ) {
            officeItems[j] = String.valueOf(office.getDid() + " " + office.getName());
            j++;
        }


        autocompleteOfficeText = binding.getRoot().findViewById(R.id.auto_complete_officeid);
        adapterOfficeItems = new ArrayAdapter<String>(getActivity(), R.layout.package_tours_list_item, officeItems);

        autocompleteOfficeText.setAdapter(adapterOfficeItems);

        autocompleteOfficeText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
            }
        });


        /////


        return root;
    }

    //insert data to db

    public void insertPackageData() {

        String tidString = binding.autoCompletePtid.getText().toString();
        String tidCut = tidString.substring(0, tidString.indexOf(" "));
        int tid = Integer.parseInt(tidCut);

        String ofidString = binding.autoCompleteOfficeid.getText().toString();
        String ofidCut = ofidString.substring(0, ofidString.indexOf(" "));
        int ofid = Integer.parseInt(ofidCut);


        int package_id = Integer.parseInt(binding.PackageId.getText().toString());
//        int package_ofid = Integer.parseInt(binding.PackageOfid.getText().toString());
//        int package_tid = Integer.parseInt(binding.packageTid.getText().toString());
        int package_departure = Integer.parseInt(binding.packageDeparture.getText().toString());
        Double package_cost = Double.parseDouble(binding.packageCost.getText().toString());

        Packages Package = new Packages();
        Package.setPid(package_id);
        Package.setDid(ofid);
        Package.setTid(tid);
        Package.setDepartureTime(package_departure);
        Package.setCost(package_cost);

        MainActivity.appDatabase.packagesDao().addPackage(Package);

        Toast.makeText(getActivity(), "Package Added Succesfully", Toast.LENGTH_LONG).show();
        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_addPackagesFragment_to_nav_packages);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}
