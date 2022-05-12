package com.iee.trvlapp.ui.Packages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.iee.trvlapp.MainActivity;
import com.iee.trvlapp.R;
import com.iee.trvlapp.databinding.FragmentPackagesBinding;
import com.iee.trvlapp.roomEntities.Packages;
import com.iee.trvlapp.roomEntities.Tours;
import com.iee.trvlapp.ui.Tours.ToursViewModel;

import java.util.List;

public class PackagesFragment extends Fragment {



    private FragmentPackagesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PackagesViewModel packagesViewModel =
                new ViewModelProvider(this).get(PackagesViewModel.class);

        binding = FragmentPackagesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        // reading room db

        List<Packages> packages = MainActivity.appDatabase.packagesDao().getPackages();

        String result ="";
        for (Packages i:  packages) {
            int  packages_id = i.getTid();
            int  packages_ofid= i.getDid();
            int packages_tid = i.getTid();
            int  packages_departure=i.getDepartureTime();
            Double  packages_cost=i.getCost();
            result = result + "\n Id: " + packages_id+ "\n Name: " + packages_ofid+ "\n Surname: " + packages_tid+ "\n duration: "+packages_departure+"\n type: "+packages_cost+"\n";

        }

        binding.packagesTextView.setText(result);

        //listener for adding packages

        binding.floatingActionButtonAddPackages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_packages_to_addPackagesFragment);
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
