package com.iee.trvlapp.ui.Costumers;

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
import com.iee.trvlapp.databinding.FragmentCostumersBinding;

public class CostumersFragment extends Fragment {


    private FragmentCostumersBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CostumersViewModel costumersViewModel =
                new ViewModelProvider(this).get(CostumersViewModel.class);

        binding = FragmentCostumersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();




        binding.floatingActionButtonAddCostumers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_costumers_to_addCostumersFragment);
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
