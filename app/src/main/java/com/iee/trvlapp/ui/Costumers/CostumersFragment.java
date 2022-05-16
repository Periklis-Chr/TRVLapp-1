package com.iee.trvlapp.ui.Costumers;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.iee.trvlapp.FirestoreEntities.Costumers;
import com.iee.trvlapp.MainActivity;
import com.iee.trvlapp.R;
import com.iee.trvlapp.databinding.FragmentCostumersBinding;
import com.iee.trvlapp.roomEntities.Offices;
import com.iee.trvlapp.ui.Offices.OfficeRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CostumersFragment extends Fragment {
    CostumerRecyclerViewAdapter adapter;

    private FragmentCostumersBinding binding;
    private List<Costumers> costumerList = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CostumersViewModel costumersViewModel =
                new ViewModelProvider(this).get(CostumersViewModel.class);

        binding = FragmentCostumersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//
////         reading rirestore db
//
//        CollectionReference collectionReference = MainActivity.appDb
//                .collection("costumers");
//        collectionReference
//                .get().addOnSuccessListener(queryDocumentSnapshots -> {
//            String result = "";
////                     List<Costumers> costumerList= new ArrayList<>();
//
//            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
//                Costumers costumers = documentSnapshot.toObject(Costumers.class);
//                String cid = costumers.getCid();
//                String costumer_name = costumers.getName();
//                String costumer_phone = costumers.getPhone();
//                String costumer_email = costumers.getEmail();
//                String costumer_pid = costumers.getPid();
//                String costumer_hotel = costumers.getHotel();
//                String costumer_lastname = costumers.getSurname();
////                        costumerList.add(costumers);
//                result = result + "\n Id: " + cid + "\n Name: " + costumer_name + "\n Surname: " + costumer_lastname + "\n duration: " + costumer_phone + "\n type: " + costumer_email + "\n pid:" + costumer_pid + "\n hotel" + costumer_hotel + "\n";
//            }
//            binding.costumersTextView.setText(result);
//
////            RecyclerView recyclerView=binding.toursRecyclerview;
////            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
////            recyclerView.setHasFixedSize(true);
////            final CostumerRecyclerViewAdapter adapter = new CostumerRecyclerViewAdapter();
////            recyclerView.setAdapter(adapter);
////
////          adapter.setCostumers(costumerList);
//
//        }).addOnFailureListener(e -> {
//            Toast.makeText(getActivity(), "Firestore read failed", Toast.LENGTH_SHORT).show();
//        });


//        RecyclerView recyclerView=binding.costumersRecyclerview;
//            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
//            recyclerView.setHasFixedSize(true);
//            final CostumerRecyclerViewAdapter adapter = new CostumerRecyclerViewAdapter();
//            recyclerView.setAdapter(adapter);
//
//
//
//
//               MainActivity.appDb
//              .collection("costumers").orderBy("name").addSnapshotListener(new EventListener<QuerySnapshot>() {
//                           @Override
//                           public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                               if(error!= null){
//                                   Log.e("cant read firstore data",error.getMessage());
//                                   return;
//                               }
//                               for(DocumentChange dc :value.getDocumentChanges()){
//                                   if(dc.getType()==DocumentChange.Type.ADDED){
//                                       costumerList.add(dc.getDocument().toObject(Costumers.class));
//                                   }
//                                   adapter.notifyDataSetChanged();
//                               }
//                           }
//                       });


        //3os tropos


        Query query = MainActivity.appDb.collection("costumers").orderBy("name", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Costumers> options = new FirestoreRecyclerOptions.Builder<Costumers>()
                .setQuery(query, Costumers.class).build();
        adapter = new CostumerRecyclerViewAdapter(options);
        RecyclerView recyclerView = binding.costumersRecyclerview;
        recyclerView.setAdapter(adapter);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));


        //listener for adding costumers


        binding.floatingActionButtonAddCostumers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_costumers_to_addCostumersFragment);
            }
        });


        return root;
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        adapter.startListening();
//    }
//
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        adapter.stopListening();
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
