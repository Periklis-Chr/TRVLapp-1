package com.iee.trvlapp.ui.Hotels;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iee.trvlapp.R;
import com.iee.trvlapp.roomEntities.CityHotels;
import com.iee.trvlapp.roomEntities.DataConverter;

import java.util.ArrayList;
import java.util.List;

public class HotelRecyclerViewAdapter extends RecyclerView.Adapter<HotelRecyclerViewAdapter.HotelHolder> {

    private List<CityHotels> hotels = new ArrayList<>();
    private HotelRecyclerViewAdapter.OnItemClickListener listener;

    public class HotelHolder extends RecyclerView.ViewHolder {
        private final TextView id;
        private final TextView name;
        private final TextView address;
        private final TextView stars;
        private final TextView tid;
        private final ImageView image;

        public HotelHolder(View view) {
            super(view);
            id = view.findViewById(R.id.hotel_row_id);
            name = view.findViewById(R.id.hotel_row_name);
            address = view.findViewById(R.id.hotel_row_address);
            stars = view.findViewById(R.id.hotel_row_stars);
            tid = view.findViewById(R.id.hotel_row_tid);
            image = view.findViewById(R.id.icon_row_hotel);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(hotels.get(position));
                    }
                }
            });
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setHotels(List<CityHotels> hotels) {
        this.hotels = hotels;
        notifyDataSetChanged();
    }


    public CityHotels getHotelAt(int position) {
        return hotels.get(position);

    }

    @NonNull
    @Override
    public HotelRecyclerViewAdapter.HotelHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_hotels_row, viewGroup, false);

        return new HotelRecyclerViewAdapter.HotelHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelRecyclerViewAdapter.HotelHolder holder, int position) {
        CityHotels currentHotel = hotels.get(position);
        holder.id.setText(String.valueOf(currentHotel.getHid()));
        holder.name.setText(currentHotel.getHotelName());
        holder.address.setText(currentHotel.getHotelAddress());
        holder.stars.setText(String.valueOf(currentHotel.getHotelStars()));
        holder.tid.setText(String.valueOf(currentHotel.getTid()));
        if (currentHotel.getImageHotel() != null) {
            holder.image.setImageBitmap(DataConverter.convertByteArray2IMage(currentHotel.getImageHotel()));
        }
    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }


    public interface OnItemClickListener {
        void onItemClick(CityHotels hotel);
    }

    public void setOnItemClickListener(HotelRecyclerViewAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

}
