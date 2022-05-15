package com.iee.trvlapp.ui.Tours;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

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
    private OnItemClickListener listener;


    public class TourHolder extends RecyclerView.ViewHolder {
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


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(tours.get(position));
                    }
                }
            });

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


    public Tours getTourAt(int position) {
        return tours.get(position);

    }


    @Override
    public int getItemCount() {
        return tours.size();
    }


    public interface OnItemClickListener {
        void onItemClick(Tours tour);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
