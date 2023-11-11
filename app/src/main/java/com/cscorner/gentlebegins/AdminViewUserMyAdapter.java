package com.cscorner.gentlebegins;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdminViewUserMyAdapter extends RecyclerView.Adapter<AdminViewUserMyAdapter.AdminViewUserMyViewHolder> {

    private final Context context;
    private final List<AdminViewUserDataClass> dataList;

    public AdminViewUserMyAdapter(Context context, List<AdminViewUserDataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public AdminViewUserMyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_view_user_recycle_item, parent, false);
        return new AdminViewUserMyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminViewUserMyViewHolder holder, int position) {
        holder.recParentName.setText(dataList.get(position).getdataParentname());
        holder.recRelationship.setText(dataList.get(position).getDataRelationship());
        holder.recBabyName.setText(dataList.get(position).getDataBabyname());
        holder.recBabyGender.setText(dataList.get(position).getDataBabygender());
        holder.recBabyBirthday.setText(dataList.get(position).getDataBabybirthday());


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class AdminViewUserMyViewHolder extends RecyclerView.ViewHolder {
        TextView recParentName, recRelationship, recBabyName, recBabyGender, recBabyBirthday;
        CardView recAdminViewCard;

        public AdminViewUserMyViewHolder(@NonNull View itemView) {
            super(itemView);

            recParentName = itemView.findViewById(R.id.viewUserName);
            recRelationship = itemView.findViewById(R.id.viewUserRelationship);
            recBabyName = itemView.findViewById(R.id.viewUserBabyname);
            recBabyGender = itemView.findViewById(R.id.viewUserBabygender);
            recBabyBirthday = itemView.findViewById(R.id.viewUserBabybirthday);
            recAdminViewCard = itemView.findViewById(R.id.adminviewuserRecCard);
        }
    }
}
