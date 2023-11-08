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
public class SolidFoodAdapter extends RecyclerView.Adapter<FViewHolder> {

    private Context context;
    private List<SolidFoodClass> solidFoodList;

    public SolidFoodAdapter(Context context, List<SolidFoodClass> solidFoodList) {
        this.context = context;
        this.solidFoodList = solidFoodList;
    }

    @NonNull
    @Override
    public FViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.solid_food_item, parent, false);
        return new FViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FViewHolder holder, int position) {
        holder.recFoodType.setText(solidFoodList.get(position).getFoodType());
        holder.recNotes.setText(solidFoodList.get(position).getFoodNotes());
        holder.recDate.setText(solidFoodList.get(position).getFoodDate());
        holder.recTime.setText(solidFoodList.get(position).getFoodTime());

    }

    @Override
    public int getItemCount() {
        return solidFoodList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void searchDataList(ArrayList<SolidFoodClass> searchList){
        solidFoodList = searchList;
        notifyDataSetChanged();
    }

}
class FViewHolder extends RecyclerView.ViewHolder{
    TextView recFoodType, recNotes, recDate, recTime;
    CardView recCard;

    public FViewHolder(@NonNull View itemView) {
        super(itemView);
        recCard = itemView.findViewById(R.id.recCard);
        recFoodType = itemView.findViewById(R.id.recFoodType);
        recNotes = itemView.findViewById(R.id.recNotes);
        recDate = itemView.findViewById(R.id.recDate);
        recTime = itemView.findViewById(R.id.recTime);
    }
}
