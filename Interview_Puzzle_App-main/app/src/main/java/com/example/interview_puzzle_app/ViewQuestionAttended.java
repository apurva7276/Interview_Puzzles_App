package com.example.interview_puzzle_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewQuestionAttended extends AppCompatActivity implements View.OnClickListener {

    public TextView selectedQuestion;
    public Button chooseNextQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_question_attended);
        getSupportActionBar().setTitle("View Question");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        selectedQuestion = findViewById(R.id.questionSelected);
        chooseNextQuestion = findViewById(R.id.btnNextQuestion);
        Intent i12 = getIntent();
        String receivedQuestion = i12.getStringExtra("passQuestion");

        selectedQuestion.setText(receivedQuestion);
        chooseNextQuestion.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}