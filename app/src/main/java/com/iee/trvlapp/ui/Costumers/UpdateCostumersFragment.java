package com.iee.trvlapp.ui.Costumers;

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

import com.google.firebase.firestore.DocumentReference;
import com.iee.trvlapp.FirestoreEntities.Costumers;
import com.iee.trvlapp.MainActivity;
import com.iee.trvlapp.R;
import com.iee.trvlapp.databinding.FragmentUpdateCostumersBinding;
import com.iee.trvlapp.databinding.FragmentUpdateToursBinding;
import com.iee.trvlapp.roomEntities.CityHotels;
import com.iee.trvlapp.roomEntities.Packages;
import com.iee.trvlapp.roomEntities.Tours;
import com.iee.trvlapp.ui.Offices.OfficesFragment;
import com.iee.trvlapp.ui.Tours.ToursFragment;
import com.iee.trvlapp.ui.Tours.ToursViewModel;

import java.util.List;

public class UpdateCostumersFragment extends Fragment {


    private FragmentUpdateCostumersBinding binding;


    ArrayAdapter<String> adapterItems;
    List<Packages> packageList;
    List<CityHotels> hotelsList;
    ArrayAdapter<String> adapterOfficeItems;

    AutoCompleteTextView autocompleteText;
    AutoCompleteTextView autocompleteOfficeText;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CostumersViewModel costumersViewModel =
                new ViewModelProvider(this).get(CostumersViewModel.class);

        binding = FragmentUpdateCostumersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        // get data between fragment transaction

        Bundle bundle = getArguments();
        int cid = bundle.getInt("cid");
        String name = bundle.getString("name");
        String surname = bundle.getString("surname");
        long phone = bundle.getLong("phone");
        String email = bundle.getString("email");
        int pid = bundle.getInt("pid");
        int hotel = bundle.getInt("hotel");


        //////

        int i = 0;

        packageList = costumersViewModel.getAllPackages();
        String[] items = new String[packageList.size()];
        int[] itemsId = new int[packageList.size()];
        for (Packages packages : packageList
        ) {
            Tours currentTour = costumersViewModel.getTourById(packages.getTid());
            items[i] = String.valueOf(packages.getPid()) + " " + currentTour.getCity() + "," + packages.getCost() + "$";
            i++;
        }


        autocompleteText = binding.getRoot().findViewById(R.id.auto_complete_costumer_pid2_update);
        adapterItems = new ArrayAdapter<String>(getActivity(), R.layout.package_tours_list_item, items);

        autocompleteText.setAdapter(adapterItems);

        autocompleteText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();


                /////


                int j = 0;

                Packages packages = costumersViewModel.getPackageById(Integer.parseInt(item.substring(0, item.indexOf(" "))));

                hotelsList = costumersViewModel.getHotelsList(packages.getTid());

                String[] hotelItems = new String[hotelsList.size()];
                for (CityHotels cityHotel : hotelsList
                ) {
                    hotelItems[j] = String.valueOf(cityHotel.getHid() + " " + cityHotel.getHotelName());
                    j++;
                }


                autocompleteOfficeText = binding.getRoot().findViewById(R.id.auto_complete_c_hotel_update);
                adapterOfficeItems = new ArrayAdapter<String>(getActivity(), R.layout.package_tours_list_item, hotelItems);

                autocompleteOfficeText.setAdapter(adapterOfficeItems);

                autocompleteOfficeText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String item2 = adapterView.getItemAtPosition(i).toString();
                    }
                });


                /////


            }
        });


        binding.updateCostumerId.setText(String.valueOf(cid));
        binding.updateFirstNameEdit.setText(name);
        binding.updateLastNameEdit.setText(surname);
        binding.updatePhoneEdit.setText(String.valueOf(phone));
        binding.updateEmailEdit.setText(email);
//        binding.updatePackageId.setText(String.valueOf(pid));
//        binding.updatePackageHotel.setText(String.valueOf(hotel));


        //Update Costumer entry when onClick

        binding.updateCostumerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(binding.updateCostumerId.getText().toString());
                String name = binding.updateFirstNameEdit.getText().toString();
                String surname = binding.updateLastNameEdit.getText().toString();
                long phone = Long.parseLong(binding.updatePhoneEdit.getText().toString());
                String email = binding.updateEmailEdit.getText().toString();


//                int pid = Integer.parseInt(binding.updatePackageId.getText().toString());
//                int hotel = Integer.parseInt(binding.updatePackageHotel.getText().toString());


                Costumers costumers = new Costumers();
                costumers.setCid(id);
                costumers.setName(name);
                costumers.setSurname(surname);
                costumers.setPhone(phone);
                costumers.setEmail(email);


//
//                costumers.setPid(pid);
//                costumers.setHotel(hotel);


                DocumentReference data = MainActivity.appDb.collection("costumers").document(String.valueOf(id));

                data.update("cid", id);
                data.update("name", name);
                data.update("surname", surname);
                data.update("email", email);
                data.update("phone", phone);


                String pidString = binding.autoCompleteCostumerPid2Update.getText().toString();
                if (!pidString.isEmpty()) {
                    String pidCut = pidString.substring(0, pidString.indexOf(" "));
                    int pid = Integer.parseInt(pidCut);
                    data.update("pid", pid);
                } else {
                    data.update("pid", pid);
                }


                String hidString = binding.autoCompleteCHotelUpdate.getText().toString();
                if (!hidString.isEmpty()) {

                    String tidCut = hidString.substring(0, hidString.indexOf(" "));
                    int hid = Integer.parseInt(tidCut);
                    data.update("hotel", hid);
                } else {
                    data.update("hotel", hotel);
                }


//                data.update("pid", pid);
//                data.update("hotel", hotel);


                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                CostumersFragment costumersFragment = new CostumersFragment();
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main, costumersFragment);
                fragmentTransaction.commit();

            }
        });


        //listener for canceling update costumer form

        binding.updateCancelCostumerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                CostumersFragment costumersFragment = new CostumersFragment();
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main, costumersFragment);
                fragmentTransaction.commit();

            }
        });


        return root;
    }

}
