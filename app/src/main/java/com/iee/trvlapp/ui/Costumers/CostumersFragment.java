package com.iee.trvlapp.ui.Costumers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.iee.trvlapp.FirestoreEntities.Costumers;
import com.iee.trvlapp.MainActivity;
import com.iee.trvlapp.R;
import com.iee.trvlapp.databinding.FragmentCostumersBinding;
import java.util.ArrayList;
import java.util.List;


public class CostumersFragment extends Fragment {
    private int showFilterFlag = 0;
    CostumerRecyclerViewAdapter adapter;
    //FirestoreRecyclerAdapter adapter;
    private FragmentCostumersBinding binding;
    private List<Costumers> costumerList = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CostumersViewModel costumersViewModel =
                new ViewModelProvider(this).get(CostumersViewModel.class);

        binding = FragmentCostumersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        // Retrieves and feeds the RecyclerViewAdapter with Costumer Data ( Firestore )

        Query query = (Query) MainActivity.appDb.collection("costumers");

        FirestoreRecyclerOptions<Costumers> options = new FirestoreRecyclerOptions.Builder<Costumers>()
                .setQuery(query, Costumers.class).build();

        adapter = new CostumerRecyclerViewAdapter(options);
        RecyclerView recyclerView = binding.costumersRecyclerview;
        recyclerView.setAdapter(adapter);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));


        //Deletes Costumer on Swipe LEFT

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.deleteItem(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(recyclerView);


        //Updates Costumer onClick

        adapter.setOnItemClickListener(new CostumerRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Costumers costumer = documentSnapshot.toObject(Costumers.class);
                int cid = costumer.getCid();
                String name = costumer.getName();
                String surname = costumer.getSurname();
                long phone = costumer.getPhone();
                String email = costumer.getEmail();
                int pid = costumer.getPid();
                int hotel = costumer.getHotel();

                Bundle bundle = new Bundle();
                bundle.putInt("cid", cid);
                bundle.putString("name", name);
                bundle.putString("surname", surname);
                bundle.putLong("phone", phone);
                bundle.putString("email", email);
                bundle.putInt("pid", pid);
                bundle.putInt("hotel", hotel);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                UpdateCostumersFragment updateCostumersFragment = new UpdateCostumersFragment();
                updateCostumersFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main, updateCostumersFragment);
                fragmentTransaction.commit();

            }
        });


        //Navigates to the AddCostumer Fragment

        binding.floatingActionButtonAddCostumers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_costumers_to_addCostumersFragment);
            }
        });

        return root;
    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
