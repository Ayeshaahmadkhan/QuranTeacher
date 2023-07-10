
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
    private ArrayList<String> student_id, student_name, student_age, sabaq_para, student_class,sabqi,manzil;

    public CustomAdapter(MainActivity2 activity, ArrayList<String> student_id, ArrayList<String> student_name, ArrayList<String> student_age, ArrayList<String> student_class, ArrayList<String> sabaq_para, ArrayList<String> sabqi, ArrayList<String> manzil) {
        this.activity = activity;
        this.student_id = student_id;
        this.student_name = student_name;
        this.student_age = student_age;
        this.student_class = student_class;
        this.sabaq_para = sabaq_para;
        this.sabqi = sabqi;
        this.manzil = manzil;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        if (position < student_id.size()) {
            holder.studentIdTxt.setText(student_id.get(position));
        } else {
            holder.studentIdTxt.setText("");
        }

        if (position < student_name.size()) {
            holder.studentNameTxt.setText(student_name.get(position));
        } else {
            holder.studentNameTxt.setText("");
        }

        if (position < student_age.size()) {
            holder.studentAgeTxt.setText(student_age.get(position));
        } else {
            holder.studentAgeTxt.setText("");
        }

        if (position < student_class.size()) {
            holder.studentClassTxt.setText(student_class.get(position));
        } else {
            holder.studentClassTxt.setText("");
        }

        if (position < sabaq_para.size()) {
            holder.sabaqParaTxt.setText(sabaq_para.get(position));
        } else {
            holder.sabaqParaTxt.setText("");
        }

        if (position < sabqi.size()) {
            holder.sabqiTxt.setText(sabqi.get(position));
        } else {
            holder.sabqiTxt.setText("");
        }

        if (position < manzil.size()) {
            holder.manzilTxt.setText(manzil.get(position));
        } else {
            holder.manzilTxt.setText("");
        }

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position < student_id.size()) {
                    String id = student_id.get(position);
                    String name = student_name.get(position);
                    String age = student_age.get(position);
                    String studentClass = student_class.get(position);
                    String paraNo = sabaq_para.get(position);
                    String sabqiData = sabqi.get(position);
                    String manzilData = manzil.get(position);

                    Intent intent = new Intent(activity, UpdateActivity.class);
                    intent.putExtra("id", id);
                    intent.putExtra("name", name);
                    intent.putExtra("age", age);
                    intent.putExtra("class", studentClass);
                    intent.putExtra("paraNo", paraNo);
                    intent.putExtra("sabqi", sabqiData);
                    intent.putExtra("manzil", manzilData);

                    activity.startActivityForResult(intent, 1);
                }
            }
        });
    }


    @Override

    public int getItemCount() {
        if (student_id != null) {
            return student_id.size();
        }
        return 0;
    }
    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView studentIdTxt, studentNameTxt, studentAgeTxt, studentClassTxt, sabaqParaTxt, sabqiTxt, manzilTxt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            studentIdTxt = itemView.findViewById(R.id.student_id_txt);
            studentNameTxt = itemView.findViewById(R.id.student_name_txt);
            studentAgeTxt = itemView.findViewById(R.id.student_age_txt);
            studentClassTxt = itemView.findViewById(R.id.student_class_txt);
            sabaqParaTxt = itemView.findViewById(R.id.sabaq_para_txt);
            sabqiTxt = itemView.findViewById(R.id.sabaqi_para_txt);
            manzilTxt = itemView.findViewById(R.id.manzil_para_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

            Animation translateAnim = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.translate_anim);
            mainLayout.setAnimation(translateAnim);
        }
    }
}

