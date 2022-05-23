package com.iee.trvlapp.ui.Offices;

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
import androidx.navigation.ui.AppBarConfiguration;
import com.iee.trvlapp.R;
import com.iee.trvlapp.databinding.FragmentUpdateOfficesBinding;
import com.iee.trvlapp.roomEntities.Offices;

public class UpdateOfficesFragment extends Fragment {
    private AppBarConfiguration mAppBarConfiguration;

    private FragmentUpdateOfficesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        OfficesViewModel officesViewModel =
                new ViewModelProvider(this).get(OfficesViewModel.class);

        binding = FragmentUpdateOfficesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        // Retrieves Data passed From Office Fragment

        Bundle bundle = getArguments();
        int id = bundle.getInt("id");
        String name = bundle.getString("name");
        String address = bundle.getString("address");

        Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();

        binding.updateOfficeName.setText(name);
        binding.updateOfficeAddress.setText(address);


        //  Updates Chosen Office and Navigates to Office Fragment

        binding.updateOfficeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.updateOfficeName.getText().toString();
                String address = binding.updateOfficeAddress.getText().toString();


                if (binding.updateOfficeName.length()!=0 && binding.updateOfficeAddress.length()!=0) {

                    Offices office = new Offices();
                    office.setOfid(id);
                    office.setName(name);
                    office.setAddress(address);
                    officesViewModel.updateOffice(office);

                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    OfficesFragment officesFragment = new OfficesFragment();
                    fragmentTransaction.replace(R.id.nav_host_fragment_content_main, officesFragment);
                    fragmentTransaction.commit();
                }else{
                    Toast.makeText(getActivity(), "Fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Update Action is Canceled and Navigates to Office Fragment

        binding.cancelUpdateOfficeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                OfficesFragment officesFragment = new OfficesFragment();
                UpdateOfficesFragment updateOfficesFragment = new UpdateOfficesFragment();
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main, officesFragment);
                fragmentTransaction.commit();

            }
        });

        return root;
    }

}
