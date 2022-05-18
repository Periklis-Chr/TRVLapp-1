package com.iee.trvlapp.ui.Costumers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.iee.trvlapp.roomEntities.Tours;
import com.iee.trvlapp.ui.Offices.OfficesFragment;
import com.iee.trvlapp.ui.Tours.ToursFragment;
import com.iee.trvlapp.ui.Tours.ToursViewModel;

public class UpdateCostumersFragment extends Fragment {


    private FragmentUpdateCostumersBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ToursViewModel toursViewModel =
                new ViewModelProvider(this).get(ToursViewModel.class);

        binding = FragmentUpdateCostumersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        // get data between fragment transaction

        Bundle bundle = getArguments();
        int cid = bundle.getInt("cid");
        String name = bundle.getString("name");
        String surname = bundle.getString("surname");
        int phone = bundle.getInt("phone");
        String email = bundle.getString("email");
        int pid = bundle.getInt("pid");
        String hotel = bundle.getString("hotel");


        binding.updateCostumerId.setText(cid);
        binding.updateFirstNameEdit.setText(name);
        binding.updateLastNameEdit.setText(surname);
        binding.updatePhoneEdit.setText(phone);
        binding.updateEmailEdit.setText(email);
        binding.updatePackageId.setText(pid);
        binding.updatePackageHotel.setText(hotel);


        //Update Costumer entry when onClick

        binding.updateCostumerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(binding.updateCostumerId.getText().toString());
                String name = binding.updateFirstNameEdit.getText().toString();
                String surname = binding.updateLastNameEdit.getText().toString();
                int phone = Integer.parseInt(binding.updatePhoneEdit.getText().toString());
                String email = binding.updateEmailEdit.getText().toString();
                int pid = Integer.parseInt(binding.updatePackageId.getText().toString());
                String hotel = binding.updatePackageHotel.getText().toString();

                Costumers costumers = new Costumers();
                costumers.setCid(id);
                costumers.setName(name);
                costumers.setSurname(surname);
                costumers.setPhone(phone);
                costumers.setEmail(email);
                costumers.setPid(pid);
                costumers.setHotel(hotel);


                DocumentReference data = MainActivity.appDb.collection("costumers").document(String.valueOf(id));

                data.update("cid", id);
                data.update("name", name);
                data.update("surname", surname);
                data.update("email", email);
                data.update("pid", pid);
                data.update("hotel", hotel);


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
