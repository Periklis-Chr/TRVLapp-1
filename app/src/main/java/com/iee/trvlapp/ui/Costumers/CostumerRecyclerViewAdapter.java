package com.iee.trvlapp.ui.Costumers;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.iee.trvlapp.FirestoreEntities.Costumers;
import com.iee.trvlapp.R;


import java.util.ArrayList;
import java.util.List;

public class CostumerRecyclerViewAdapter extends FirestoreRecyclerAdapter<Costumers,CostumerRecyclerViewAdapter.CostumerHolder> {


    public CostumerRecyclerViewAdapter(@NonNull FirestoreRecyclerOptions<Costumers> options) {
        super(options);
    }

    public class CostumerHolder extends RecyclerView.ViewHolder {
//         TextView id;
//         TextView name;
//         TextView surname;
//         TextView phone;
//        TextView email;
//         TextView pid;
        TextView hotel;

        public CostumerHolder(View view) {
            super(view);
//            id = view.findViewById(R.id.costumer_row_id);
//            name = view.findViewById(R.id.costumer_row_name);
//            surname = view.findViewById(R.id.costumer_row_surname);
//            phone = view.findViewById(R.id.costumer_row_phone);
//            email = view.findViewById(R.id.costumer_row_email);
//            pid = view.findViewById(R.id.costumer_row_pid);
            hotel = view.findViewById(R.id.costumer_row_hotel);
        }
    }



    @NonNull
    @Override
    public CostumerHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_packages_row, viewGroup, false);

        return new CostumerHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull CostumerHolder holder, int position, @NonNull Costumers model) {


//        holder.id.setText(model.getCid()+"");
//        holder.name.setText(model.getName() + "");
//        holder.surname.setText(model.getSurname() + "");
//        holder.phone.setText(model.getPhone() + "");
//        holder.email.setText(model.getEmail() + "");
//        holder.pid.setText(model.getPid() + "");
        holder.hotel.setText(model.getHotel() + "");
    }
}

