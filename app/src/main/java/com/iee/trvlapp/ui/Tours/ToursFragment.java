package com.iee.trvlapp.ui.Tours;

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
import com.iee.trvlapp.databinding.FragmentToursBinding;

public class ToursFragment extends Fragment {

    private FragmentToursBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ToursViewModel toursViewModel =
                new ViewModelProvider(this).get(ToursViewModel.class);

        binding = FragmentToursBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textTours;
        toursViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


binding.floatingActionButtonAddTours.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Navigation.findNavController(view).navigate(R.id.action_nav_tours_to_addToursFragment);
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