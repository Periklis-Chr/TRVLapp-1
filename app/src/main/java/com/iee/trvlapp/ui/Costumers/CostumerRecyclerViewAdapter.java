package com.iee.trvlapp.ui.Costumers;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iee.trvlapp.FirestoreEntities.Costumers;
import com.iee.trvlapp.R;


import java.util.ArrayList;
import java.util.List;

public class CostumerRecyclerViewAdapter extends RecyclerView.Adapter<CostumerRecyclerViewAdapter.CostumerHolder> {
    private List<Costumers> costumers = new ArrayList<>();

    public static class CostumerHolder extends RecyclerView.ViewHolder {
        private final TextView id;
        private final TextView name;
        private final TextView surname;
        private final TextView phone;
        private final TextView email;
        private final TextView pid;
        private final TextView hotel;

        public CostumerHolder(View view) {
            super(view);
            id = view.findViewById(R.id.costumer_row_id);
            name = view.findViewById(R.id.costumer_row_name);
            surname = view.findViewById(R.id.costumer_row_surname);
            phone = view.findViewById(R.id.costumer_row_phone);
            email = view.findViewById(R.id.costumer_row_email);
            pid = view.findViewById(R.id.costumer_row_pid);
            hotel = view.findViewById(R.id.costumer_row_hotel);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setCostumers(List<Costumers> costumers) {
        this.costumers = costumers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CostumerRecyclerViewAdapter.CostumerHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_packages_row, viewGroup, false);

        return new CostumerRecyclerViewAdapter.CostumerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CostumerRecyclerViewAdapter.CostumerHolder holder, int position) {
        Costumers currentCostumer = costumers.get(position);


        holder.id.setText(currentCostumer.getCid());
        holder.name.setText(currentCostumer.getName() + "");
        holder.surname.setText(currentCostumer.getSurname() + "");
        holder.phone.setText(currentCostumer.getPhone() + "");
        holder.email.setText(currentCostumer.getEmail() + "");
        holder.pid.setText(currentCostumer.getPid() + "");
        holder.hotel.setText(currentCostumer.getHotel() + "");
    }

    @Override
    public int getItemCount() {
        return costumers.size();
    }
}
