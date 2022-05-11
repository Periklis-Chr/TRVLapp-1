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

import com.iee.trvlapp.R;
import com.iee.trvlapp.databinding.FragmentPackagesBinding;
import com.iee.trvlapp.ui.Tours.ToursViewModel;

public class PackagesFragment extends Fragment {



    private FragmentPackagesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PackagesViewModel packagesViewModel =
                new ViewModelProvider(this).get(PackagesViewModel.class);

        binding = FragmentPackagesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textPackages;
        packagesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);



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
