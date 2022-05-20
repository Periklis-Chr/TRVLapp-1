package com.iee.trvlapp.ui.Offices;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
//import android.widget.Toolbar;

import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.NavigatorState;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.iee.trvlapp.MainActivity;
import com.iee.trvlapp.R;
import com.iee.trvlapp.databinding.ActivityMainBinding;
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


        //// get data between fragment transaction

        Bundle bundle = getArguments();
        int id = bundle.getInt("id");
        String name = bundle.getString("name");
        String address = bundle.getString("address");
        Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
//        binding.updateOfficeId.setText(String.valueOf(id));
        binding.updateOfficeName.setText(name);
        binding.updateOfficeAddress.setText(address);

        //  Updates Office onclick

        binding.updateOfficeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int id = Integer.parseInt(binding.updateOfficeId.getText().toString());
                String name = binding.updateOfficeName.getText().toString();
                String address = binding.updateOfficeAddress.getText().toString();
                Offices office = new Offices();
                office.setDid(id);
                office.setName(name);
                office.setAddress(address);
                officesViewModel.updateOffice(office);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                OfficesFragment officesFragment = new OfficesFragment();
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main, officesFragment);
                fragmentTransaction.commit();

            }
        });

        //cancel update form

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
