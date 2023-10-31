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
public class SleepAdapter extends RecyclerView.Adapter<SViewHolder>{

    private Context context;
    private List<SleepingClass> sleepList;

    public SleepAdapter(Context context, List<SleepingClass> sleepList) {
        this.context = context;
        this.sleepList = sleepList;
    }

    @NonNull
    @Override
    public SViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sleep_item, parent, false);
        return new SViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SViewHolder holder, int position) {

        holder.recSleepMode.setText(sleepList.get(position).getSleepMode());
        holder.recDuration.setText(sleepList.get(position).getDuration());
        holder.recStart.setText(sleepList.get(position).getTimeStart());
        holder.recEnd.setText(sleepList.get(position).getTimeEnd());





    }

    @Override
    public int getItemCount() {
        return sleepList.size();
    }
}

class SViewHolder extends RecyclerView.ViewHolder{
    TextView recSleepMode,recDuration,recStart,recEnd;
    CardView recCard;

    public SViewHolder(@NonNull View itemView) {
        super(itemView);
        recCard = itemView.findViewById(R.id.recCard);
        recSleepMode = itemView.findViewById(R.id.recSleepMode);
        recDuration = itemView.findViewById(R.id.recDuration);
        recStart = itemView.findViewById(R.id.recStart);
        recEnd = itemView.findViewById(R.id.recEnd);


    }
}
