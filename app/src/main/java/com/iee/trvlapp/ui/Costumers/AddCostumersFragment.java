package com.iee.trvlapp.ui.Costumers;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class AddCostumersFragment extends Fragment {
    private FragmentAddCostumersBinding binding;

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


        return root;
    }


    //Insert Costumer Data to Firestore

    public void insertCostumersData() {

        String costumer_id = binding.costumerId.getText().toString();
        String costumer_name = binding.firstNameEdit.getText().toString();
        String costumer_surname = binding.lastNameEdit.getText().toString();
        String costumer_phone = binding.phoneEdit.getText().toString();
        String costumer_email = binding.emailEdit.getText().toString();
        String costumer_pid = binding.packageId.getText().toString();
        String costumer_hotel = binding.packageHotel.getText().toString();


        Costumers costumer = new Costumers();
        costumer.setCid(costumer_id);
        costumer.setName(costumer_name);
        costumer.setSurname(costumer_surname);
        costumer.setPhone(costumer_phone);
        costumer.setEmail(costumer_email);
        costumer.setPid(costumer_pid);
        costumer.setHotel(costumer_hotel);

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
        binding.packageHotel.setText("");
        binding.packageId.setText("");
        binding.packageHotel.setText("");

        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_addCostumersFragment_to_nav_costumers);
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
