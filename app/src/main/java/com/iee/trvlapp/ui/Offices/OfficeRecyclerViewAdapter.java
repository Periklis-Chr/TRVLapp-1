package com.iee.trvlapp.ui.Offices;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.iee.trvlapp.R;
import com.iee.trvlapp.roomEntities.Offices;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OfficeRecyclerViewAdapter extends RecyclerView.Adapter<OfficeRecyclerViewAdapter.OfficeHolder> {

    private List<Offices> offices=new ArrayList<>();

    public static class OfficeHolder extends RecyclerView.ViewHolder {
        private final TextView id;
        private final TextView name;
        private final TextView address;

        public OfficeHolder(View view) {
            super(view);
            id = view.findViewById(R.id.office_row_id);
            name = view.findViewById(R.id.office_row_name);
            address = view.findViewById(R.id.office_row_address);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setOffices(List<Offices> offices){
        this.offices=offices;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OfficeHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_offices_row, viewGroup, false);

        return new OfficeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfficeHolder holder, int position) {
        Offices currentOffice= offices.get(position);
        holder.id.setText(String.valueOf(currentOffice.getDid()));
        holder.name.setText(currentOffice.getName());
        holder.address.setText(currentOffice.getAddress());

    }

    @Override
    public int getItemCount() {
        return offices.size();
    }
}