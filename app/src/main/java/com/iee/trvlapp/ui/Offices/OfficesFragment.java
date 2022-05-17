package com.iee.trvlapp.ui.Offices;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.iee.trvlapp.MainActivity;
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


        // TODO

//            Toolbar toolbar=root.findViewById(R.id.toolbar);
//
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar) ;
//
//        DrawerLayout drawer = root.findViewById(R.id.drawer_layout);
//        NavigationView navigationView =root.findViewById(R.id.nav_view);
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_offices, R.id.nav_tours, R.id.nav_packages, R.id.nav_costumers, R.id.action_updateOfficesFragment_to_nav_offices)
//                .setOpenableLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
//         NavigationUI.setupActionBarWithNavController(getActivity().getApplicationContext(), navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
//
//


        RecyclerView recyclerView = binding.officeRecyclerview;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        final OfficeRecyclerViewAdapter adapter = new OfficeRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);


        // Retrieves Data from Room db

        officesViewModel.getAllOffices().observe(getViewLifecycleOwner(), new Observer<List<Offices>>() {
            @Override
            public void onChanged(List<Offices> offices) {
                adapter.setOffices(offices);
            }
        });


        //Deletes data from Room db on Swipe LEFT


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


        //Updates data from Room db onClick


        adapter.setOnItemClickListener(new OfficeRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Offices office) {
                String id = String.valueOf(office.getDid());
                String name = office.getName().toString();
                String address = office.getAddress().toString();

                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                bundle.putString("name", name);
                bundle.putString("address", address);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                UpdateOfficesFragment updateOfficesFragment = new UpdateOfficesFragment();
                updateOfficesFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main, updateOfficesFragment);
                fragmentTransaction.commit();
            }
        });


        //listener for adding offices

        binding.floatingActionButtonAddOffices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_offices_to_addOfficesFragment);
            }
        });


        binding.fabFiltering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupMenu(view);
            }
        });

        return root;
    }


    // Sorting filters for the reyclerView

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