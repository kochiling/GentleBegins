package com.cscorner.gentlebegins;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;
import java.util.Objects;

public class TaskAdapter extends RecyclerView.Adapter<TViewHolder> {

    private Context context;
    private List<TaskClass> taskList;
    DatabaseReference databaseReference;

    public TaskAdapter(Context context, List<TaskClass> taskList) {
        this.context = context;
        this.taskList = taskList;
    }
    @NonNull
    @Override
    public TViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull TViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.recTitle.setText(taskList.get(position).getTaskTitle());
        holder.recDesc.setText(taskList.get(position).getTaskDesc());
        holder.recDt.setText(taskList.get(position).getTaskDT());
    }


    @Override
    public int getItemCount() {
        return taskList.size();
    }

}
class TViewHolder extends RecyclerView.ViewHolder{
    TextView recTitle, recDesc, recDt;
    CheckBox checkBox;
    CardView recCard;

    public TViewHolder(@NonNull View itemView) {
        super(itemView);
        recCard = itemView.findViewById(R.id.recCard);
        recDesc = itemView.findViewById(R.id.recDesc);
        recDt = itemView.findViewById(R.id.recDt);
        recTitle = itemView.findViewById(R.id.recTitle);
        checkBox = itemView.findViewById(R.id.checkBox);


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    Toast.makeText(itemView.getContext(), "Done Task", Toast.LENGTH_SHORT).show();
                    recTitle.setPaintFlags(recTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    recDesc.setPaintFlags(recDesc.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    recTitle.setPaintFlags(0); // Remove the strike-through
                    recDesc.setPaintFlags(0);  // Remove the strike-through
                }
            }
        });
    }
}