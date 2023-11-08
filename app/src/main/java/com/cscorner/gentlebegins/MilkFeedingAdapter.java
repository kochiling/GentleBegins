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

public class MilkFeedingAdapter extends RecyclerView.Adapter <MilkViewHolder> {

    private Context context;
    private List<MilkFeedingClass> milkList;

    public MilkFeedingAdapter(Context context, List<MilkFeedingClass> milkList) {
        this.context = context;
        this.milkList = milkList;
    }

    @NonNull
    @Override
    public MilkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.milkfeeding_item, parent, false);
        return new MilkViewHolder(view);
    }


    public void onBindViewHolder(@NonNull MilkViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.recMilkType.setText(milkList.get(position).getMilkType());
        holder.recAmount.setText(milkList.get(position).getMilkAmount());
        holder.recUnit.setText(milkList.get(position).getMilkUnit());
        holder.recDate.setText(milkList.get(position).getMilkDate());
        holder.recTime.setText(milkList.get(position).getMilkTime());
    }

    @Override
    public int getItemCount() {
        return milkList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void searchDataList(ArrayList<MilkFeedingClass> searchList){
        milkList = searchList;
        notifyDataSetChanged();
    }
}

class MilkViewHolder extends RecyclerView.ViewHolder{
    TextView recMilkType,recAmount,recUnit,recDate,recTime;
    CardView recCard;

    public MilkViewHolder(@NonNull View itemView) {
        super(itemView);
        recCard = itemView.findViewById(R.id.recCard);
        recMilkType = itemView.findViewById(R.id.recMilkType);
        recUnit = itemView.findViewById(R.id.recUnit);
        recAmount = itemView.findViewById(R.id.recAmount);
        recDate = itemView.findViewById(R.id.recDate);
        recTime = itemView.findViewById(R.id.recTime);

    }

}
