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
public class DiaperAdapter extends RecyclerView.Adapter<DViewHolder> {

    private Context context;
    private List<DiaperClass> diaperList;

    public DiaperAdapter(Context context, List<DiaperClass> diaperList) {
        this.context = context;
        this.diaperList = diaperList;
    }

    @NonNull
    @Override
    public DViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diaper_item, parent, false);
        return new DViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DViewHolder holder, int position) {
        holder.recStatus.setText(diaperList.get(position).getDiaperStatus());
        holder.recNotes.setText(diaperList.get(position).getDiaperNotes());
        holder.recDate.setText(diaperList.get(position).getDiaperDate());
        holder.recTime.setText(diaperList.get(position).getDiaperTime());
    }

    @Override
    public int getItemCount() {
        return diaperList.size();
    }
}

class DViewHolder extends RecyclerView.ViewHolder{
    TextView recStatus, recNotes, recDate, recTime;
    CardView recCard;

    public DViewHolder(@NonNull View itemView) {
        super(itemView);
        recCard = itemView.findViewById(R.id.recCard);
        recStatus = itemView.findViewById(R.id.recStatus);
        recNotes = itemView.findViewById(R.id.recNotes);
        recDate = itemView.findViewById(R.id.recDate);
        recTime = itemView.findViewById(R.id.recTime);

    }
}
