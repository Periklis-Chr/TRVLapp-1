package com.iee.trvlapp.ui.Packages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
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

import com.iee.trvlapp.MainActivity;
import com.iee.trvlapp.R;
import com.iee.trvlapp.databinding.FragmentPackagesBinding;
import com.iee.trvlapp.roomEntities.Offices;
import com.iee.trvlapp.roomEntities.Packages;
import com.iee.trvlapp.roomEntities.Tours;
import com.iee.trvlapp.ui.Offices.OfficeRecyclerViewAdapter;
import com.iee.trvlapp.ui.Offices.OfficesViewModel;
import com.iee.trvlapp.ui.Offices.UpdateOfficesFragment;
import com.iee.trvlapp.ui.Tours.ToursViewModel;

import java.util.List;

public class PackagesFragment extends Fragment {


    private FragmentPackagesBinding binding;
    private int showFilterFlag = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PackagesViewModel packagesViewModel =
                new ViewModelProvider(this).get(PackagesViewModel.class);

        binding = FragmentPackagesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        //Retrieves data from Room db

        RecyclerView recyclerView = binding.packageRecyclerview;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        final PackageRecyclerViewAdapter adapter = new PackageRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        packagesViewModel.getAllPackages().observe(getViewLifecycleOwner(), new Observer<List<Packages>>() {
            @Override
            public void onChanged(List<Packages> packages) {
                adapter.setPackages(packages);
            }
        });


        //Delete data on Swipe LEFT


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                packagesViewModel.deletePackage(adapter.getPackageAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(), "Package deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);


        //Updates data onClick


        adapter.setOnItemClickListener(new PackageRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Packages packages) {
                int id = packages.getDid();
                int ofid = packages.getDid();
                int tid = packages.getTid();
                int departure = packages.getDepartureTime();
                double cost = packages.getCost();

                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                bundle.putInt("ofid", ofid);
                bundle.putInt("tid", tid);
                bundle.putInt("departure", departure);
                bundle.putDouble("cost", cost);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                UpdatePackagesFragment updatePackagesFragment = new UpdatePackagesFragment();
                updatePackagesFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main, updatePackagesFragment);
                fragmentTransaction.commit();
            }
        });


        //listener for adding packages

        binding.floatingActionButtonAddPackages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_packages_to_addPackagesFragment);
            }
        });

        binding.fabFilteringPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupMenu(view);
            }
        });

        return root;
    }


    //sortin filters for RecyclerView

    public void popupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(getActivity().getApplicationContext(), view);
        popupMenu.setOnMenuItemClickListener(this::onMenuItemClick);
        popupMenu.inflate(R.menu.package_filters);
        popupMenu.show();
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        PackagesViewModel packagesViewModel = new ViewModelProvider(this).get(PackagesViewModel.class);
        RecyclerView recyclerView = binding.packageRecyclerview;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        final PackageRecyclerViewAdapter adapter = new PackageRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        switch (menuItem.getItemId()) {
            case R.id.p_filter_des_alphabetical_name:

                packagesViewModel.getPackagesOrderByNameDesc().observe(getViewLifecycleOwner(), new Observer<List<Packages>>() {
                    @Override
                    public void onChanged(List<Packages> packages) {
                        adapter.setPackages(packages);
                    }
                });
                showFilterFlag = 1;
                return true;
            case R.id.p_filter_asc_alphabetical_name:


                packagesViewModel.getPackagesOrderByNameAsc().observe(getViewLifecycleOwner(), new Observer<List<Packages>>() {
                    @Override
                    public void onChanged(List<Packages> packages) {
                        adapter.setPackages(packages);
                    }
                });

                showFilterFlag = 2;
                return true;
            case R.id.p_filter_id:

                packagesViewModel.getAllPackages().observe(getViewLifecycleOwner(), new Observer<List<Packages>>() {
                    @Override
                    public void onChanged(List<Packages> packages) {
                        adapter.setPackages(packages);
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
