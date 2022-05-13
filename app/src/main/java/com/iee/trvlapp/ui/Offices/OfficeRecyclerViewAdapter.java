package com.iee.trvlapp.ui.Offices;
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

    private List<Offices> offices ;
    @NonNull
    @Override
    public OfficeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_offices_row,parent,false);

        return new OfficeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfficeHolder holder, int position) {

        Offices currentOffice= offices.get(position);
        holder.id.setText(currentOffice.getDid());
        holder.name.setText(currentOffice.getName());
        holder.address.setText(currentOffice.getAddress());

    }

    @Override
    public int getItemCount() {
        return offices.size();
    }


    public void setOffices(List<Offices> Offices){

        this.offices=offices;

    }




    class OfficeHolder extends RecyclerView.ViewHolder{
        private TextView id;
        private TextView name;
        private TextView address;

        public OfficeHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.office_row_id);
            name=itemView.findViewById(R.id.office_row_name);
            address=itemView.findViewById(R.id.office_row_address);
        }
    }

}
