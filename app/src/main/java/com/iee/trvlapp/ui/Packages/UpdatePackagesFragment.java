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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.iee.trvlapp.R;
import com.iee.trvlapp.databinding.FragmentUpdateOfficesBinding;
import com.iee.trvlapp.databinding.FragmentUpdatePackagesBinding;
import com.iee.trvlapp.roomEntities.Offices;
import com.iee.trvlapp.roomEntities.Packages;
import com.iee.trvlapp.roomEntities.Tours;
import com.iee.trvlapp.ui.Offices.OfficesFragment;
import com.iee.trvlapp.ui.Offices.OfficesViewModel;
import com.iee.trvlapp.ui.Offices.UpdateOfficesFragment;

import java.util.List;

public class UpdatePackagesFragment extends Fragment {

    private FragmentUpdatePackagesBinding binding;
    AutoCompleteTextView autocompleteText;
    AutoCompleteTextView autocompleteOfficeText;

    ArrayAdapter<String> adapterItems;
    List<Tours> toursList;
    List<Offices> officesList;
    ArrayAdapter<String> adapterOfficeItems;


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
        boolean flagTour = bundle.getBoolean("flagTour");
         boolean flagOffice =bundle.getBoolean("flagOffice");










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


        autocompleteText = binding.getRoot().findViewById(R.id.auto_complete_ptid_update);
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


        autocompleteOfficeText = binding.getRoot().findViewById(R.id.auto_complete_officeid_update);
        adapterOfficeItems = new ArrayAdapter<String>(getActivity(), R.layout.package_tours_list_item, officeItems);

        autocompleteOfficeText.setAdapter(adapterOfficeItems);

        autocompleteOfficeText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
            }
        });


        /////





//        binding.updatePackageId.setText(String.valueOf(id));
//        binding.updatePackageOfid.setText(String.valueOf(ofid));
//        binding.updatePackageTid.setText(String.valueOf(tid));
        binding.updatePackageDeparture.setText(String.valueOf(departure));
        binding.updatePackageCost.setText(String.valueOf(cost));

        //onclick listener for updating Room data

        binding.updatePackageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int id = Integer.parseInt(binding.updatePackageId.getText().toString());
//                int ofid = Integer.parseInt(binding.updatePackageOfid.getText().toString());
//                int tid = Integer.parseInt(binding.updatePackageTid.getText().toString());
                int departure = Integer.parseInt(binding.updatePackageDeparture.getText().toString());
                Double cost = Double.parseDouble(binding.updatePackageCost.getText().toString());


                Packages packages = new Packages();
                packages.setPid(id);



                    String ofidString = binding.autoCompleteOfficeidUpdate.getText().toString();
                if (!ofidString.isEmpty()) {
                    String ofidCut = ofidString.substring(0, ofidString.indexOf(" "));
                    int ofid = Integer.parseInt(ofidCut);
                    packages.setDid(ofid);
                } else {
                    packages.setDid(ofid);
                }



                    String tidString = binding.autoCompletePtidUpdate.getText().toString();
                if (!tidString.isEmpty()) {

                    String tidCut = tidString.substring(0, tidString.indexOf(" "));
                    int tid = Integer.parseInt(tidCut);
                    packages.setTid(tid);
                } else {
                    packages.setTid(tid);
                }


                packages.setDepartureTime(departure);
                packages.setCost(cost);
                packagesViewModel.updatePackage(packages);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                PackagesFragment packagesFragment = new PackagesFragment();
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main, packagesFragment);
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
