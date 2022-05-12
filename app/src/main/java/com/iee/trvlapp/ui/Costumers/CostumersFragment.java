package com.iee.trvlapp.ui.Costumers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.iee.trvlapp.FirestoreEntities.Costumers;
import com.iee.trvlapp.MainActivity;
import com.iee.trvlapp.R;
import com.iee.trvlapp.databinding.FragmentCostumersBinding;

import java.util.Collection;

public class CostumersFragment extends Fragment {


    private FragmentCostumersBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CostumersViewModel costumersViewModel =
                new ViewModelProvider(this).get(CostumersViewModel.class);

        binding = FragmentCostumersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

    // reading rirestore db

        CollectionReference collectionReference= MainActivity.appDb
                .collection("costumers");
        collectionReference
                .get().addOnSuccessListener(queryDocumentSnapshots -> {
                    String result="";

                    for(QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){
                        Costumers costumers=documentSnapshot.toObject(Costumers.class);
                        String cid=costumers.getCid();
                        String costumer_name=costumers.getName();
                        String costumer_lastname=costumers.getSurname();
                        String costumer_phone=costumers.getPhone();
                        String costumer_email=costumers.getEmail();
                        String costumer_pid=costumers.getPid();
                        String costumer_hotel=costumers.getHotel();

                        result = result + "\n Id: " + cid + "\n Name: " + costumer_name+ "\n Surname: " + costumer_lastname+ "\n duration: "+costumer_phone+"\n type: "+costumer_email+"\n pid:"+costumer_pid+"\n hotel"+costumer_hotel +"\n";
                    }
                    binding.costumersTextView.setText(result);
        }).addOnFailureListener(e -> {
            Toast.makeText(getActivity(), "Firestore read failed", Toast.LENGTH_SHORT).show();
        });


        //listener for adding costumers


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
