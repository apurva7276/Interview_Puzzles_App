package com.example.interview_puzzle_app.ui.PracticeMode.QueList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.interview_puzzle_app.R;
import com.example.interview_puzzle_app.ui.PracticeMode.CategoryAdapter;
import com.example.interview_puzzle_app.ui.PracticeMode.CategoryModel;
import com.example.interview_puzzle_app.ui.QuestionUI.QuestionUiActivity;

import java.util.ArrayList;

public class QueAdapter extends RecyclerView.Adapter<QueAdapter.QueViewHolder> {

    Context context;
    ArrayList<QueModel> queModels;

    public QueAdapter(Context context, ArrayList<QueModel> queModels){
        this.context = context;
        this.queModels = queModels;
    }

    @NonNull
    @Override
    public QueAdapter.QueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_que_list_que, parent, false);
        return new QueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QueViewHolder holder, int position) {
        QueModel model = queModels.get(position);

        holder.PQLQueTV.setText(model.getShortQ());
        holder.PQLnoTV.setText(""+(position+1));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, QuestionUiActivity.class);
                intent.putExtra("Question", model);
                intent.putExtra("NameBtn","Submit");
                intent.putExtra("QueList",queModels);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return queModels.size();
    }

    public class QueViewHolder extends RecyclerView.ViewHolder {

        TextView PQLQueTV,PQLnoTV;

        public QueViewHolder(@NonNull View itemView) {
            super(itemView);
            //PQLnoTV - Practice Que Layout number Text View
            PQLnoTV = itemView.findViewById(R.id.PQLnoTV);
            PQLQueTV = itemView.findViewById(R.id.PQLQueTV);
        }
    }
}
