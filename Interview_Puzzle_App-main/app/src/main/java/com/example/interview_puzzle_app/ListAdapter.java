package com.example.interview_puzzle_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<QuestionHistory> {

    public ListAdapter(Context context, ArrayList<QuestionHistory> questionHistoriesList){
        super(context, R.layout.list_items, questionHistoriesList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        QuestionHistory questionHistory = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_items,parent,false);
        }

        TextView qNo = convertView.findViewById(R.id.questionNumber);
        TextView question = convertView.findViewById(R.id.questionText);

        qNo.setText(questionHistory.questionNo);
        question.setText(questionHistory.question);

        return convertView;
        //return super.getView(position, convertView, parent);
    }
}
