package com.iee.trvlapp.ui.Tours;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iee.trvlapp.R;
import com.iee.trvlapp.roomEntities.Offices;
import com.iee.trvlapp.roomEntities.Tours;
import com.iee.trvlapp.ui.Offices.OfficeRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class TourRecyclerViewAdapter extends RecyclerView.Adapter<TourRecyclerViewAdapter.TourHolder> {
    private List<Tours> tours = new ArrayList<>();

    public static class TourHolder extends RecyclerView.ViewHolder {
        private final TextView id;
        private final TextView city;
        private final TextView country;
        private final TextView duration;
        private final TextView type;

        public TourHolder(View view) {
            super(view);
            id = view.findViewById(R.id.tour_row_id);
            city = view.findViewById(R.id.tour_row_city);
            country = view.findViewById(R.id.tour_row_country);
            duration = view.findViewById(R.id.tour_row_duration);
            type = view.findViewById(R.id.tour_row_type);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setTours(List<Tours> tours) {
        this.tours = tours;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TourRecyclerViewAdapter.TourHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_tours_row, viewGroup, false);

        return new TourRecyclerViewAdapter.TourHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TourRecyclerViewAdapter.TourHolder holder, int position) {
        Tours currentTour = tours.get(position);
        holder.id.setText(currentTour.getTid() + "");
        holder.city.setText(currentTour.getCity());
        holder.country.setText(currentTour.getCountry());
        holder.duration.setText(currentTour.getDuration());
        holder.type.setText(currentTour.getType());

    }

    @Override
    public int getItemCount() {
        return tours.size();
    }
}
