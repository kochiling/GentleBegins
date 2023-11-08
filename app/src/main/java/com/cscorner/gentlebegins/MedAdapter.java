package com.cscorner.gentlebegins;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MedAdapter extends RecyclerView.Adapter<MedAdapter.MyViewHolder> {

    private Context context;
    private List<MedDataClass> dataList;

    public MedAdapter(Context context, List<MedDataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MedDataClass medData = dataList.get(position);

        holder.medamount.setText(medData.getMedAmount());
        holder.medate.setText(medData.getMedDate());
        holder.medsymptoms.setText(medData.getMedSymptoms());
        holder.medtype.setText(medData.getMedicineType());
        holder.medtime.setText(medData.getMedTime());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView medamount, medate, medsymptoms, medtype, medtime;
        CardView medCard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            medamount = itemView.findViewById(R.id.medamounts);
            medate = itemView.findViewById(R.id.medates);
            medsymptoms = itemView.findViewById(R.id.medsymptom);
            medtype = itemView.findViewById(R.id.medtypes);
            medtime = itemView.findViewById(R.id.medtimes);
            medCard = itemView.findViewById(R.id.medCard);
        }
    }
}
