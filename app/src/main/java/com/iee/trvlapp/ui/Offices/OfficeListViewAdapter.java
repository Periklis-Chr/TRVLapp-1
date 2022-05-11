package com.iee.trvlapp.ui.Offices;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iee.trvlapp.R;
import com.iee.trvlapp.roomEntities.Offices;

import java.util.List;

public class OfficeListViewAdapter extends RecyclerView.Adapter<OfficeListViewAdapter.ViewHolder> {

    List<Offices> offices;

    public OfficeListViewAdapter(List<Offices> offices){
        this.offices=offices;
    }


    @NonNull
    @Override
    public OfficeListViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.office_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfficeListViewAdapter.ViewHolder holder, int position) {
        holder.id.setText(offices.get(position).getDid());
        holder.name.setText( offices.get(position).getName());
        holder.address.setText(offices.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return offices.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView id;
        public TextView name;
        public TextView address;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.office_row_id);
            name=itemView.findViewById(R.id.office_row_name);
            address=itemView.findViewById(R.id.office_row_address);

        }
    }
}



