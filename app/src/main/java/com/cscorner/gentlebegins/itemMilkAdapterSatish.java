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

import java.util.ArrayList;
import java.util.List;

public class itemMilkAdapterSatish extends RecyclerView.Adapter <itemMilkViewHolder> {

    private Context context;
    private List<itemMilkClassSatish> milkList;

    public itemMilkAdapterSatish(Context context, List<itemMilkClassSatish> milkList) {
        this.context = context;
        this.milkList = milkList;
    }

    @NonNull
    @Override
    public itemMilkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.milkfeedingitem_satish, parent, false);
        return new itemMilkViewHolder(view);
    }


    public void onBindViewHolder(@NonNull itemMilkViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.itemMilkType.setText(milkList.get(position).getItemMilkType());
        holder.itemAmount.setText(milkList.get(position).getItemMilkAmount());
        holder.itemUnit.setText(milkList.get(position).getItemMilkUnit());
        holder.itemDate.setText(milkList.get(position).getItemMilkDate());
        holder.itemTime.setText(milkList.get(position).getItemMilkTime());
    }

    @Override
    public int getItemCount() {
        return milkList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void searchDataList(ArrayList<itemMilkClassSatish> searchList){
        milkList = searchList;
        notifyDataSetChanged();
    }
}

class itemMilkViewHolder extends RecyclerView.ViewHolder{
    TextView itemMilkType, itemAmount, itemUnit, itemDate, itemTime;
    CardView itemMilkCard;

    public itemMilkViewHolder(@NonNull View itemView) {
        super(itemView);
        itemMilkCard = itemView.findViewById(R.id.itemMilkCard);
        itemMilkType = itemView.findViewById(R.id.itemMilkType);
        itemAmount = itemView.findViewById(R.id.itemAmount);
        itemUnit = itemView.findViewById(R.id.itemUnit);
        itemDate = itemView.findViewById(R.id.itemDate);
        itemTime = itemView.findViewById(R.id.itemTime);

    }

}
