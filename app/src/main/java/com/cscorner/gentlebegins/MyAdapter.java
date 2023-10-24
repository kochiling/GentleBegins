package com.cscorner.gentlebegins;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    public MyAdapter(Context context, ArrayList list) {
        this.context = context;
        this.list = list;
    }

    ArrayList<User> list;





    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

     User user = list.get(position);
    holder.todaysdate.setText(user.getFeedingdate());
    holder.foodsnotes.setText(user.getFoodnotess());
    holder.foodstype.setText(user.getFoodtypess());
    holder.times.setText(user.getTimess());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView todaysdate,foodsnotes,foodstype,times;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            todaysdate = itemView.findViewById(R.id.todaydate);
            foodsnotes = itemView.findViewById(R.id.foodnotes);
            foodstype = itemView.findViewById(R.id.foodtype);
            times = itemView.findViewById(R.id.time);
        }
    }
}
