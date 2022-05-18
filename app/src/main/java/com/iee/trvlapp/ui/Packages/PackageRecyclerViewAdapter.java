package com.iee.trvlapp.ui.Packages;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iee.trvlapp.R;
import com.iee.trvlapp.roomEntities.Offices;
import com.iee.trvlapp.roomEntities.Packages;
import com.iee.trvlapp.ui.Offices.OfficeRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class PackageRecyclerViewAdapter extends RecyclerView.Adapter<PackageRecyclerViewAdapter.PackageHolder> {

    private List<Packages> packages = new ArrayList<>();
    private OnItemClickListener listener;

    public class PackageHolder extends RecyclerView.ViewHolder {
        private final TextView id;
        private final TextView ofid;
        private final TextView tid;
        private final TextView departure;
        private final TextView cost;

        public PackageHolder(View view) {
            super(view);
            id = view.findViewById(R.id.package_row_id);
            ofid = view.findViewById(R.id.package_row_ofid);
            tid = view.findViewById(R.id.package_row_tid);
            departure = view.findViewById(R.id.package_row_departure);
            cost = view.findViewById(R.id.package_row_cost);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(packages.get(position));
                    }
                }
            });

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setPackages(List<Packages> packages) {
        this.packages = packages;
        notifyDataSetChanged();
    }


    public Packages getPackageAt(int position) {
        return packages.get(position);

    }


    @NonNull
    @Override
    public PackageRecyclerViewAdapter.PackageHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_packages_row, viewGroup, false);

        return new PackageRecyclerViewAdapter.PackageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PackageRecyclerViewAdapter.PackageHolder holder, int position) {
        Packages currentPackage = packages.get(position);


        holder.id.setText(String.valueOf(currentPackage.getPid()));
        holder.ofid.setText(String.valueOf(currentPackage.getDid()));
        holder.tid.setText(String.valueOf(currentPackage.getTid()));
        holder.departure.setText(String.valueOf(currentPackage.getDepartureTime()));
        holder.cost.setText(String.valueOf(currentPackage.getCost()));

    }

    @Override
    public int getItemCount() {
        return packages.size();
    }


    public interface OnItemClickListener {
        void onItemClick(Packages packages);
    }

    public void setOnItemClickListener(PackageRecyclerViewAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }


}
