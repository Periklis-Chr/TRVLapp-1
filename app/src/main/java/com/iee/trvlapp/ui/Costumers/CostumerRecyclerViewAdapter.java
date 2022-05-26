package com.iee.trvlapp.ui.Costumers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.iee.trvlapp.FirestoreEntities.Costumers;
import com.iee.trvlapp.MainActivity;
import com.iee.trvlapp.R;
import com.iee.trvlapp.roomEntities.CityHotels;
import com.iee.trvlapp.roomEntities.Packages;
import com.iee.trvlapp.roomEntities.Tours;


public class CostumerRecyclerViewAdapter extends FirestoreRecyclerAdapter<Costumers, CostumerRecyclerViewAdapter.CostumerHolder> {
    private OnItemClickListener listener;


    public CostumerRecyclerViewAdapter(@NonNull FirestoreRecyclerOptions<Costumers> options) {
        super(options);
    }

    public void deleteItem(int position) {
        getSnapshots().getSnapshot(position).getReference().delete();

    }

    public class CostumerHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView name;
        TextView surname;
        TextView phone;
        TextView email;
        TextView pid;
        TextView hotel;
        TextView city;


        public CostumerHolder(View view) {
            super(view);


                id = view.findViewById(R.id.costumer_row_id);
                name = view.findViewById(R.id.costumer_row_name);
                surname = view.findViewById(R.id.costumer_row_surname);
                phone = view.findViewById(R.id.costumer_row_phone);
                email = view.findViewById(R.id.costumer_row_email);
                pid = view.findViewById(R.id.costumer_row_pid);
                hotel = view.findViewById(R.id.costumer_row_hotel);
                city = view.findViewById(R.id.costumer_row_city);


                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION && listener != null ) {
                            listener.onItemClick(getSnapshots().getSnapshot(position), position);
                        }
                    }
                });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CostumerHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_costumers, viewGroup, false);
        return new CostumerHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull CostumerHolder holder, int position, @NonNull Costumers model) {

            Packages currentPackage = MainActivity.appDatabase.packagesDao().getPackageById(model.getPid());

        if(currentPackage!=null) {
            Tours currentTour = MainActivity.appDatabase.toursDao().getTourById(currentPackage.getTid());
            CityHotels cityHotels = MainActivity.appDatabase.cityHotelsDao().getCityHotelById(model.getHotel());

            holder.id.setText(String.valueOf(model.getCid()));
            holder.name.setText(String.valueOf(model.getName()));
            holder.surname.setText(String.valueOf(model.getSurname()));
            holder.phone.setText(String.valueOf(model.getPhone()));
            holder.email.setText(String.valueOf(model.getEmail()));
            holder.pid.setText(String.valueOf(model.getPid()));
            holder.city.setText(currentTour.getCity());
            holder.hotel.setText(cityHotels.getHotelName());

        }else{
//            MainActivity.appDb.collection("costumers").document(String.valueOf(model.getCid())).delete();
            deleteItem(position);

        }
    }
}

