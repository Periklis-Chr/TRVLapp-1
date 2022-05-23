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
import com.iee.trvlapp.roomEntities.CityHotels;
import com.iee.trvlapp.roomEntities.Packages;
import com.iee.trvlapp.roomEntities.Tours;

import java.util.List;

public class UpdateCostumersFragment extends Fragment {


    private FragmentUpdateCostumersBinding binding;

    ArrayAdapter<String> adapterItems;
    List<Packages> packageList;
    List<CityHotels> hotelsList;
    ArrayAdapter<String> adapterHotelItems;

    AutoCompleteTextView autocompleteText;
    AutoCompleteTextView autocompleteHotelText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CostumersViewModel costumersViewModel =
                new ViewModelProvider(this).get(CostumersViewModel.class);

        binding = FragmentUpdateCostumersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        // Retrieves Data passed From Costumers Fragment

        Bundle bundle = getArguments();
        int cid = bundle.getInt("cid");
        String name = bundle.getString("name");
        String surname = bundle.getString("surname");
        long phone = bundle.getLong("phone");
        String email = bundle.getString("email");
        int pid = bundle.getInt("pid");
        int hotel = bundle.getInt("hotel");


        // Supports Dynamic autocomplete ListView for package_id on UpdateCostumer Fragment

        int i = 0;

        packageList = costumersViewModel.getAllPackages();
        String[] items = new String[packageList.size()];
        for (Packages packages : packageList
        ) {
            Tours currentTour = costumersViewModel.getTourById(packages.getTid());
            items[i] = String.valueOf(packages.getPid()) + " " + currentTour.getCity() + "," + packages.getCost() + "$";
            i++;
        }


        autocompleteText = binding.getRoot().findViewById(R.id.auto_complete_costumer_pid2_update);
        adapterItems = new ArrayAdapter<String>(getActivity(), R.layout.auto_complete_list_item, items);

        autocompleteText.setAdapter(adapterItems);
        autocompleteText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();


                // Supports Dynamic autocomplete ListView for hotel_id on UpdateCostumer Fragment


                int j = 0;

                Packages packages = costumersViewModel.getPackageById(Integer.parseInt(item.substring(0, item.indexOf(" "))));
                hotelsList = costumersViewModel.getHotelsList(packages.getTid());

                String[] hotelItems = new String[hotelsList.size()];
                for (CityHotels cityHotel : hotelsList
                ) {
                    hotelItems[j] = String.valueOf(cityHotel.getHid() + " " + cityHotel.getHotelName());
                    j++;
                }

                autocompleteHotelText = binding.getRoot().findViewById(R.id.auto_complete_c_hotel_update);
                adapterHotelItems = new ArrayAdapter<String>(getActivity(), R.layout.auto_complete_list_item, hotelItems);

                autocompleteHotelText.setAdapter(adapterHotelItems);
                autocompleteHotelText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String item2 = adapterView.getItemAtPosition(i).toString();
                    }
                });

            }
        });


        binding.updateFirstNameEdit.setText(name);
        binding.updateLastNameEdit.setText(surname);
        binding.updatePhoneEdit.setText(String.valueOf(phone));
        binding.updateEmailEdit.setText(email);


        //  Updates Chosen Costumer and Navigates to Costumers Fragment

        binding.updateCostumerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.updateFirstNameEdit.getText().toString();
                String surname = binding.updateLastNameEdit.getText().toString();
                long phone = Long.parseLong(binding.updatePhoneEdit.getText().toString());
                String email = binding.updateEmailEdit.getText().toString();

                Costumers costumers = new Costumers();
                costumers.setCid(cid);
                costumers.setName(name);
                costumers.setSurname(surname);
                costumers.setPhone(phone);
                costumers.setEmail(email);

                DocumentReference data = MainActivity.appDb.collection("costumers").document(String.valueOf(cid));

                data.update("cid", cid);
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

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                CostumersFragment costumersFragment = new CostumersFragment();
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main, costumersFragment);
                fragmentTransaction.commit();

            }
        });


        //Update Action is Canceled and Navigates to Costumers Fragment

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
