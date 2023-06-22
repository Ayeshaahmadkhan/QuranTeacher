package com.example.qurandiary;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Activity activity;
    private ArrayList<String> student_id, student_name, student_age, sabaq_para, student_class;

    public CustomAdapter(Activity activity, ArrayList<String> student_id, ArrayList<String> student_name,
                         ArrayList<String> student_age, ArrayList<String> student_class, ArrayList<String> sabaq_para) {
        this.activity = activity;
        this.student_id = student_id;
        this.student_name = student_name;
        this.student_age = student_age;
        this.student_class = student_class;
        this.sabaq_para = sabaq_para;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.studentIdTxt.setText(student_id.get(position));
        holder.studentNameTxt.setText(student_name.get(position));
        holder.studentAgeTxt.setText(student_age.get(position));
        holder.studentClassTxt.setText(student_class.get(position));
        holder.sabaqParaTxt.setText(sabaq_para.get(position));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, UpdateActivity.class);
                intent.putExtra("id", student_id.get(holder.getAdapterPosition()));
                intent.putExtra("name", student_name.get(holder.getAdapterPosition()));
                intent.putExtra("age", student_age.get(holder.getAdapterPosition()));
                intent.putExtra("class", student_class.get(holder.getAdapterPosition()));
                intent.putExtra("paraNo", sabaq_para.get(holder.getAdapterPosition()));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return student_id.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView studentIdTxt, studentNameTxt, studentAgeTxt, studentClassTxt, sabaqParaTxt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            studentIdTxt = itemView.findViewById(R.id.student_id_txt);
            studentNameTxt = itemView.findViewById(R.id.student_name_txt);
            studentAgeTxt = itemView.findViewById(R.id.student_age_txt);
            studentClassTxt = itemView.findViewById(R.id.student_class_txt);
            sabaqParaTxt = itemView.findViewById(R.id.sabaq_para_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

            Animation translateAnim = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.translate_anim);
            mainLayout.setAnimation(translateAnim);
        }
    }
}
