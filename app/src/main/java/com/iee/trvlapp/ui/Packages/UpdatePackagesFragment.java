package com.iee.trvlapp.ui.Packages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.iee.trvlapp.R;
import com.iee.trvlapp.databinding.FragmentUpdatePackagesBinding;
import com.iee.trvlapp.roomEntities.Offices;
import com.iee.trvlapp.roomEntities.Packages;
import com.iee.trvlapp.roomEntities.Tours;

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


        // Retrieves Data passed From Packages Fragment

        Bundle bundle = getArguments();
        int id = bundle.getInt("id");
        int office_id = bundle.getInt("ofid");
        int tour_id = bundle.getInt("tid");
        int departure = bundle.getInt("departure");
        double cost = bundle.getDouble("cost");


        // Supports Dynamic autocomplete ListView for tour_id on UpdatePackage Fragment

        int i = 0;

        toursList = packagesViewModel.getAllTours();
        String[] items = new String[toursList.size()];

        for (Tours tour : toursList
        ) {
            items[i] = String.valueOf(tour.getTid()) + " " + tour.getCity();
            i++;
        }

        autocompleteText = binding.getRoot().findViewById(R.id.auto_complete_ptid_update);
        adapterItems = new ArrayAdapter<String>(getActivity(), R.layout.auto_complete_list_item, items);

        autocompleteText.setAdapter(adapterItems);
        autocompleteText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
            }
        });

        // Supports Dynamic autocomplete ListView for office_id on UpdatePackage Fragment

        int j = 0;

        officesList = packagesViewModel.getAllOffices();
        String[] officeItems = new String[officesList.size()];
        for (Offices office : officesList
        ) {
            officeItems[j] = String.valueOf(office.getOfid() + " " + office.getName());
            j++;
        }

        autocompleteOfficeText = binding.getRoot().findViewById(R.id.auto_complete_officeid_update);
        adapterOfficeItems = new ArrayAdapter<String>(getActivity(), R.layout.auto_complete_list_item, officeItems);

        autocompleteOfficeText.setAdapter(adapterOfficeItems);
        autocompleteOfficeText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
            }
        });


        binding.updatePackageDeparture.setText(String.valueOf(departure));
        binding.updatePackageCost.setText(String.valueOf(cost));


        //  Updates Chosen Package and Navigates to Packages Fragment

        binding.updatePackageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int departure = Integer.parseInt(binding.updatePackageDeparture.getText().toString());
                Double cost = Double.parseDouble(binding.updatePackageCost.getText().toString());

                Packages packages = new Packages();
                packages.setPid(id);

                String office_idString = binding.autoCompleteOfficeidUpdate.getText().toString();
                if (!office_idString.isEmpty()) {
                    String office_idCut = office_idString.substring(0, office_idString.indexOf(" "));
                    int office_id = Integer.parseInt(office_idCut);
                    packages.setOfid(office_id);
                } else {
                    packages.setOfid(office_id);
                }


                String tour_idString = binding.autoCompletePtidUpdate.getText().toString();
                if (!tour_idString.isEmpty()) {
                    String tour_idCut = tour_idString.substring(0, tour_idString.indexOf(" "));
                    int tour_id = Integer.parseInt(tour_idCut);
                    packages.setTid(tour_id);
                } else {
                    packages.setTid(tour_id);
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


        // Update Action is Canceled and Navigates to Packages Fragment

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
