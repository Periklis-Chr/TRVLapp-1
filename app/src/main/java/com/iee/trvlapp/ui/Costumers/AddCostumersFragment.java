package com.iee.trvlapp.ui.Costumers;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.iee.trvlapp.FirestoreEntities.Costumers;
import com.iee.trvlapp.MainActivity;
import com.iee.trvlapp.R;
import com.iee.trvlapp.databinding.FragmentAddCostumersBinding;
import com.iee.trvlapp.roomEntities.CityHotels;
import com.iee.trvlapp.roomEntities.Offices;
import com.iee.trvlapp.roomEntities.Packages;
import com.iee.trvlapp.roomEntities.Tours;

import java.util.List;


public class AddCostumersFragment extends Fragment {
    private FragmentAddCostumersBinding binding;


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

        binding = FragmentAddCostumersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        //listener for confirmation of data insertion

        binding.costumerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertCostumersData();
            }
        });


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


        autocompleteText = binding.getRoot().findViewById(R.id.auto_complete_costumer_pid2);
        adapterItems = new ArrayAdapter<String>(getActivity(), R.layout.package_tours_list_item, items);

        autocompleteText.setAdapter(adapterItems);

        autocompleteText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();


                /////


                int j = 0;

                Packages packages=costumersViewModel.getPackageById(Integer.parseInt(item.substring(0, item.indexOf(" "))));

                hotelsList = costumersViewModel.getHotelsList(packages.getTid());

//                hotelsList = costumersViewModel.getHotelsList(Integer.parseInt(item.substring(0, item.indexOf(" "))));
                String[] hotelItems = new String[hotelsList.size()];
                for (CityHotels cityHotel : hotelsList
                ) {
                    hotelItems[j] = String.valueOf(cityHotel.getHid() + " " + cityHotel.getHotelName());
                    j++;
                }


                autocompleteOfficeText = binding.getRoot().findViewById(R.id.auto_complete_c_hotel);
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


        return root;
    }


    //Insert Costumer Data to Firestore

    public void insertCostumersData() {


//        if (binding.costumerId.length() != 0 && binding.firstNameEdit.length() != 0 && binding.lastNameEdit.length() != 0 && binding.phoneEdit.length() != 0 && binding.emailEdit.length() != 0 && binding.packageId.length() != 0 && binding.packageHotel.length() != 0) {
        if (binding.costumerId.length() != 0 && binding.firstNameEdit.length() != 0 && binding.lastNameEdit.length() != 0 && binding.phoneEdit.length() != 0 && binding.emailEdit.length() != 0 && binding.autoCompleteCostumerPid2.length() != 0 && binding.autoCompleteCHotel.length() != 0) {


            int costumer_id = Integer.parseInt(binding.costumerId.getText().toString());
            String costumer_name = binding.firstNameEdit.getText().toString();
            String costumer_surname = binding.lastNameEdit.getText().toString();
            long costumer_phone = Long.parseLong(binding.phoneEdit.getText().toString());
            String costumer_email = binding.emailEdit.getText().toString();


//            int costumer_pid = Integer.parseInt(binding.packageId.getText().toString());
//            int costumer_hotel = Integer.parseInt(binding.packageHotel.getText().toString());


            int pid = 0;
            int hid = 0;
            String pidString = binding.autoCompleteCostumerPid2.getText().toString();
            if (!pidString.isEmpty()) {
                String pidCut = pidString.substring(0, pidString.indexOf(" "));
                pid = Integer.parseInt(pidCut);
            }
            String hidString = binding.autoCompleteCHotel.getText().toString();
            if (!hidString.isEmpty()) {
                String hidCut = hidString.substring(0, hidString.indexOf(" "));
                hid = Integer.parseInt(hidCut);
            }


            Costumers costumer = new Costumers();
            costumer.setCid(costumer_id);
            costumer.setName(costumer_name);
            costumer.setSurname(costumer_surname);
            costumer.setPhone(costumer_phone);
            costumer.setEmail(costumer_email);
            costumer.setPid(pid);
            costumer.setHotel(hid);

            MainActivity.appDb.collection("costumers")
                    .document("" + costumer_id)
                    .set(costumer)
                    .addOnCompleteListener((task) -> {
                        Toast.makeText(getActivity(), "data added on firestore", Toast.LENGTH_LONG).show();

                        pushNotification();

                    })
                    .addOnFailureListener((e) -> {
                        Toast.makeText(getActivity(), "failed to add data on firestore", Toast.LENGTH_LONG).show();
                    });

            binding.costumerId.setText("");
            binding.firstNameEdit.setText("");
            binding.lastNameEdit.setText("");
            binding.emailEdit.setText("");
            binding.phoneEdit.setText("");
//            binding.packageHotel.setText("");
//            binding.packageId.setText("");
//            binding.packageHotel.setText("");
            Toast.makeText(getActivity(), "Costumer Added", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_addCostumersFragment_to_nav_costumers);


        } else {
            Toast.makeText(getActivity(), "fill all fields", Toast.LENGTH_SHORT).show();
        }


    }


    // function for onAdd Success notification


    public void pushNotification() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("notificationId", "notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getActivity().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), "notificationId")
                .setContentText("TRVl")
                .setSmallIcon(R.mipmap.tick_icon)
                .setAutoCancel(true)
                .setContentText("New Costumer Added on Firestore!!!");

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getActivity());
        managerCompat.notify(999, builder.build());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
