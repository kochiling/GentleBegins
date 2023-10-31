package com.cscorner.gentlebegins;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class MedicineAdapter extends RecyclerView.Adapter<MedViewHolder> {

    private Context context;
    private List<MedicineClass> medicineList;


    public MedicineAdapter(Context context, List<MedicineClass> medicineList) {
        this.context = context;
        this.medicineList = medicineList;
    }

    @NonNull
    @Override
    public MedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine_items, parent, false);
        return new MedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.recSymptom.setText(medicineList.get(position).getMedSymptoms());
        holder.recMed.setText(medicineList.get(position).getMedicineType());
        holder.recAmount.setText(medicineList.get(position).getMedAmount());
        holder.recDate.setText(medicineList.get(position).getMedDate());
        holder.recTime.setText(medicineList.get(position).getMedTime());
    }

    @Override
    public int getItemCount() {
        return medicineList.size();
    }

}

class MedViewHolder extends RecyclerView.ViewHolder{
    TextView recSymptom, recMed, recDate, recTime, recAmount;
    CardView recCard;

    public MedViewHolder(@NonNull View itemView) {
        super(itemView);
        recCard = itemView.findViewById(R.id.recCard);
        recSymptom = itemView.findViewById(R.id.recSymptom);
        recMed = itemView.findViewById(R.id.recMedicine);
        recAmount = itemView.findViewById(R.id.recAmount);
        recDate = itemView.findViewById(R.id.recDate);
        recTime = itemView.findViewById(R.id.recTime);

    }
}