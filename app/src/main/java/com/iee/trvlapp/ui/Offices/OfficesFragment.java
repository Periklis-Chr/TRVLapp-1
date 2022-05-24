package com.iee.trvlapp.ui.Offices;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iee.trvlapp.R;
import com.iee.trvlapp.databinding.FragmentOfficesBinding;
import com.iee.trvlapp.roomEntities.Offices;

import java.util.List;

public class OfficesFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {

    private FragmentOfficesBinding binding;
    private AppBarConfiguration mAppBarConfiguration;

    private int showFilterFlag;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        OfficesViewModel officesViewModel =
                new ViewModelProvider(this).get(OfficesViewModel.class);

        binding = FragmentOfficesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        RecyclerView recyclerView = binding.officeRecyclerview;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        final OfficeRecyclerViewAdapter adapter = new OfficeRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);


        // Retrieves and feeds the RecyclerViewAdapter with Offices Data

        officesViewModel.getAllOffices().observe(getViewLifecycleOwner(), new Observer<List<Offices>>() {
            @Override
            public void onChanged(List<Offices> offices) {
                adapter.setOffices(offices);
            }
        });


        //Deletes Office on Swipe LEFT


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                officesViewModel.deleteOffice(adapter.getOfficeAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(), "Office deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);


        //Updates Office onClick

        adapter.setOnItemClickListener(new OfficeRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Offices office) {
                int id = office.getOfid();
                String name = office.getName().toString();
                String address = office.getAddress().toString();

                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                bundle.putString("name", name);
                bundle.putString("address", address);
                bundle.putByteArray("image",office.getImage());
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                UpdateOfficesFragment updateOfficesFragment = new UpdateOfficesFragment();
                updateOfficesFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main, updateOfficesFragment);
                fragmentTransaction.commit();
            }
        });


        //Navigates to the AddOffice Fragment

        binding.floatingActionButtonAddOffices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_offices_to_addOfficesFragment);
            }
        });


        //Gets support and populates menu for filter options

        binding.fabFiltering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupMenu(view);
            }
        });

        return root;
    }


    // Filter handling for Offices List

    public void popupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(getActivity().getApplicationContext(), view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.office_filters);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        OfficesViewModel officesViewModel = new ViewModelProvider(this).get(OfficesViewModel.class);
        RecyclerView recyclerView = binding.officeRecyclerview;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        final OfficeRecyclerViewAdapter adapter = new OfficeRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        switch (menuItem.getItemId()) {
            case R.id.filter_des_alphabetical_name:

                officesViewModel.getofficesOrderByNameDesc().observe(getViewLifecycleOwner(), new Observer<List<Offices>>() {
                    @Override
                    public void onChanged(List<Offices> offices) {
                        adapter.setOffices(offices);
                    }
                });
                showFilterFlag = 1;
                return true;
            case R.id.filter_asc_alphabetical_name:


                officesViewModel.getofficesOrderByNameAsc().observe(getViewLifecycleOwner(), new Observer<List<Offices>>() {
                    @Override
                    public void onChanged(List<Offices> offices) {
                        adapter.setOffices(offices);
                    }
                });

                showFilterFlag = 2;
                return true;
            case R.id.filter_id:

                officesViewModel.getAllOffices().observe(getViewLifecycleOwner(), new Observer<List<Offices>>() {
                    @Override
                    public void onChanged(List<Offices> offices) {
                        adapter.setOffices(offices);
                    }
                });


                showFilterFlag = 0;
                return true;
            default:
                return false;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}