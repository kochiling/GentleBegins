package com.cscorner.gentlebegins;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AddTipsMyAdapter extends RecyclerView.Adapter<AddTipsMyAdapter.AddTipsMyViewHolder> {
    private final Context context;
    private final List<AddTips_DataClass> dataList;

    public AddTipsMyAdapter(Context context, List<AddTips_DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public AddTipsMyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.additional_tips_recycle_item, parent, false);
        return new AddTipsMyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddTipsMyViewHolder holder, int position) {
        Glide.with(context).load(dataList.get(position).getDataImage()).into(holder.recImage);
        holder.recTitle.setText(dataList.get(position).getDataTitle());
        holder.recLink.setText(dataList.get(position).getDataLink());
        holder.recDesc.setText(dataList.get(position).getDataDesc());

        holder.recCard.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), AddTipsDetailActivity.class);
            intent.putExtra("Image", dataList.get(holder.getAdapterPosition()).getDataImage());
            intent.putExtra("Title", dataList.get(holder.getAdapterPosition()).getDataTitle());
            intent.putExtra("URL Link", dataList.get(holder.getAdapterPosition()).getDataLink());
            intent.putExtra("Description", dataList.get(holder.getAdapterPosition()).getDataDesc());
            intent.putExtra("Key",dataList.get(holder.getAdapterPosition()).getKey());
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class AddTipsMyViewHolder extends RecyclerView.ViewHolder {
        ImageView recImage;
        TextView recTitle, recLink, recDesc;
        CardView recCard;

        public AddTipsMyViewHolder(@NonNull View itemView) {
            super(itemView);

            recImage = itemView.findViewById(R.id.addtipsRecImage);
            recTitle = itemView.findViewById(R.id.addtipsRecTitle);
            recLink = itemView.findViewById(R.id.addtipsRecLink);
            recDesc = itemView.findViewById(R.id.addtipsRecDesc);
            recCard = itemView.findViewById(R.id.addtipsRecCard);
        }
    }
}
