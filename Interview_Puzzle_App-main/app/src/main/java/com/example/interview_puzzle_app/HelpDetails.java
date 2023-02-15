package com.example.interview_puzzle_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HelpDetails extends AppCompatActivity {

    TextView tvProblem,tvSolution;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_details);
        getSupportActionBar().setTitle("Help");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvProblem = findViewById(R.id.tvProblemClicked);
        tvSolution = findViewById(R.id.tvSolution);
        //tv1.setText(R.string.problem1);
        Intent getProblem = getIntent();
        String sentProblem= getProblem.getStringExtra("problem");
        tvProblem.setText(sentProblem);
        //if(sentProblem.equals(R.string.problemA1))
        //String s1 = String.valueOf(R.string.fixProblem1);
       // if(sentProblem.equalsIgnoreCase("Troubleshoot problem solving contest"))
        if(sentProblem.equalsIgnoreCase("Troubleshoot problem solving contest"))
        {
            tvSolution.setText(R.string.fixProblem1);
            Toast.makeText(getApplicationContext(),"Solution:",Toast.LENGTH_SHORT).show();
        }
        else {
            tvSolution.setText("No action");
        }
        if(sentProblem.equalsIgnoreCase("Trouble account issues"))
        {
            tvSolution.setText(R.string.fixProblem2);
        }
        if(sentProblem.equalsIgnoreCase("Troubleshoot hint YouTube video errors, buffering and freezing"))
        {
            tvSolution.setText(R.string.fixProblem3);
        }
        if(sentProblem.equalsIgnoreCase("Use your Google account for Interview Puzzle App"))
        {
            tvSolution.setText(R.string.manageAccount1);
        }
        if(sentProblem.equalsIgnoreCase("Why sign in to Interview Puzzle App"))
        {
            tvSolution.setText(R.string.manageAccount2);
        }
        if(sentProblem.equalsIgnoreCase("Policies"))
        {
            tvSolution.setText(R.string.policySafetyCopyright1);
        }
        if(sentProblem.equalsIgnoreCase("Privacy and safety centre"))
        {
            tvSolution.setText(R.string.policySafetyCopyright2);
        }
        if(sentProblem.equalsIgnoreCase("Copyright and rights management"))
        {
            //enter copyright text solution
        }
    }
}