package com.iee.trvlapp.ui.Tours;

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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.iee.trvlapp.R;
import com.iee.trvlapp.databinding.FragmentToursBinding;
import com.iee.trvlapp.roomEntities.Tours;

import java.util.List;

public class ToursFragment extends Fragment {

    private FragmentToursBinding binding;
    private int showFilterFlag = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ToursViewModel toursViewModel =
                new ViewModelProvider(this).get(ToursViewModel.class);

        binding = FragmentToursBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Retrieves and feeds the RecyclerViewAdapter with Tours Data

        RecyclerView recyclerView = binding.tourRecyclerview;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        final TourRecyclerViewAdapter adapter = new TourRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        toursViewModel.getAllTours().observe(getViewLifecycleOwner(), new Observer<List<Tours>>() {
            @Override
            public void onChanged(List<Tours> tours) {
                adapter.setTours(tours);
            }
        });


        // Navigates to TourMap Fragment on Swipe RIGHT and shows the corresponding location


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                String CityName = adapter.getTourAt(viewHolder.getAbsoluteAdapterPosition()).getCity();

                Bundle bundle = new Bundle();
                bundle.putString("id", CityName);


                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ToursMapsFragment toursMapsFragment = new ToursMapsFragment();
                toursMapsFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main, toursMapsFragment);
                fragmentTransaction.commit();

            }
        }).attachToRecyclerView(recyclerView);


        //Deletes Tour on Swipe LEFT

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                toursViewModel.deleteTour(adapter.getTourAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(), "Tour deleted !", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);


        //Updates Tour onClick

        adapter.setOnItemClickListener(new TourRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Tours tours) {
                int id = tours.getTid();
                String city = tours.getCity().toString();
                String country = tours.getCountry().toString();
                int duration = tours.getDuration();
                String type = tours.getType().toString();

                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                bundle.putString("city", city);
                bundle.putString("country", country);
                bundle.putInt("duration", duration);
                bundle.putString("type", type);
                bundle.putByteArray("image",tours.getImageTour());

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                UpdateToursFragment updatetoursFragment = new UpdateToursFragment();
                updatetoursFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main, updatetoursFragment);
                fragmentTransaction.commit();
            }
        });


        //Navigates to the AddTour Fragment

        binding.floatingActionButtonAddTours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_tours_to_addToursFragment);
            }
        });

//        binding.fabFilteringTour.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                popupMenu(view);
//            }
//        });


        return root;
    }


    //Gets support and populates menu for filter options


    public void popupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(getActivity().getApplicationContext(), view);
        popupMenu.setOnMenuItemClickListener(this::onMenuItemClick);
        popupMenu.inflate(R.menu.tour_filters);
        popupMenu.show();
    }


    // Filter handling for Offices List

    public boolean onMenuItemClick(MenuItem menuItem) {
        ToursViewModel toursViewModel = new ViewModelProvider(this).get(ToursViewModel.class);
        RecyclerView recyclerView = binding.tourRecyclerview;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        final TourRecyclerViewAdapter adapter = new TourRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        switch (menuItem.getItemId()) {
            case R.id.t_filter_des_alphabetical_name:
                filter_des_alphabetical_name:

                toursViewModel.getToursOrderByNameDesc().observe(getViewLifecycleOwner(), new Observer<List<Tours>>() {
                    @Override
                    public void onChanged(List<Tours> tours) {
                        adapter.setTours(tours);
                    }
                });
                showFilterFlag = 1;
                return true;
            case R.id.t_filter_asc_alphabetical_name:


                toursViewModel.getToursOrderByNameAsc().observe(getViewLifecycleOwner(), new Observer<List<Tours>>() {
                    @Override
                    public void onChanged(List<Tours> tours) {
                        adapter.setTours(tours);
                    }
                });

                showFilterFlag = 2;
                return true;
            case R.id.t_filter_id:

                toursViewModel.getAllTours().observe(getViewLifecycleOwner(), new Observer<List<Tours>>() {
                    @Override
                    public void onChanged(List<Tours> tours) {
                        adapter.setTours(tours);
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