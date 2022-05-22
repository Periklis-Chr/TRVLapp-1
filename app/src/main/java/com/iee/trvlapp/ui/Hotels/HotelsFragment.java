package com.iee.trvlapp.ui.Hotels;

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
import com.iee.trvlapp.databinding.FragmentHotelsBinding;
import com.iee.trvlapp.databinding.FragmentOfficesBinding;
import com.iee.trvlapp.roomEntities.CityHotels;
import com.iee.trvlapp.roomEntities.Offices;
import com.iee.trvlapp.ui.Offices.OfficeRecyclerViewAdapter;
import com.iee.trvlapp.ui.Offices.OfficesViewModel;
import com.iee.trvlapp.ui.Offices.UpdateOfficesFragment;

import java.util.List;

public class HotelsFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {


    private FragmentHotelsBinding binding;
    private int showFilterFlag;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HotelsViewModel hotelsViewModel =
                new ViewModelProvider(this).get(HotelsViewModel.class);

        binding = FragmentHotelsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        RecyclerView recyclerView = binding.hotelRecyclerview;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        final HotelRecyclerViewAdapter adapter = new HotelRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);


        // Retrieves Data from Room db

        hotelsViewModel.getAllHotels().observe(getViewLifecycleOwner(), new Observer<List<CityHotels>>() {
            @Override
            public void onChanged(List<CityHotels> hotels) {
                adapter.setHotels(hotels);
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
                hotelsViewModel.deleteHotel(adapter.getHotelAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(), "Hotel deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);


        //Updates data from Room db onClick


        adapter.setOnItemClickListener(new HotelRecyclerViewAdapter.OnItemClickListener() {
            //            @Override
            public void onItemClick(CityHotels hotel) {
                int id = hotel.getHid();
                String name = hotel.getHotelName();
                String address = hotel.getHotelAddress();
                int stars = hotel.getHotelStars();
                int tid = hotel.getTid();

                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                bundle.putString("name", name);
                bundle.putString("address", address);
                bundle.putInt("stars", stars);
                bundle.putInt("tid", tid);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                UpdateHotelsFragment updateHotelsFragment = new UpdateHotelsFragment();
                updateHotelsFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main, updateHotelsFragment);

                fragmentTransaction.commit();
            }
        });


        //listener for adding offices

        binding.floatingActionButtonAddHotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_hotels_to_addHotelsFragment);
            }
        });


        binding.fabFilteringHotels.setOnClickListener(new View.OnClickListener() {
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
        popupMenu.inflate(R.menu.hotel_filters);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        HotelsViewModel hotelsViewModel = new ViewModelProvider(this).get(HotelsViewModel.class);
        RecyclerView recyclerView = binding.hotelRecyclerview;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        final HotelRecyclerViewAdapter adapter = new HotelRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        switch (menuItem.getItemId()) {
            case R.id.h_filter_des_alphabetical_name:

                hotelsViewModel.getHotelsOrderByNameDesc().observe(getViewLifecycleOwner(), new Observer<List<CityHotels>>() {
                    @Override
                    public void onChanged(List<CityHotels> cityHotels) {
                        adapter.setHotels(cityHotels);
                    }
                });
                showFilterFlag = 1;
                return true;
            case R.id.h_filter_asc_alphabetical_name:


                hotelsViewModel.getHotelsOrderByNameAsc().observe(getViewLifecycleOwner(), new Observer<List<CityHotels>>() {
                    @Override
                    public void onChanged(List<CityHotels> cityHotels) {
                        adapter.setHotels(cityHotels);
                    }
                });

                showFilterFlag = 2;
                return true;
            case R.id.h_filter_id:

                hotelsViewModel.getAllHotels().observe(getViewLifecycleOwner(), new Observer<List<CityHotels>>() {
                    @Override
                    public void onChanged(List<CityHotels> cityHotels) {
                        adapter.setHotels(cityHotels);
                    }
                });


                showFilterFlag = 0;
                return true;
            default:
                return false;
        }
    }

}
