package com.example.interview_puzzle_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class QuestionActivity extends AppCompatActivity {

    // public TextView timerTv;
    //public TextView dynamicTextView;
    public TextView scoredown, scoreMessage;
    String  attendedQ, notAttendedQ, correctAns, incorrectAns;
    public View dividerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        getSupportActionBar().setTitle("Score");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*timerTv = (TextView) findViewById(R.id.tv_timer);
        long duration = TimeUnit.SECONDS.toMillis(5);
        new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                //When tick then convert milliseconds to minute and seconds
                String sDuration = String.format(Locale.ENGLISH,"%02d : %02d"
                        , TimeUnit.MILLISECONDS.toMinutes(l)
                        , TimeUnit.MILLISECONDS.toSeconds(l)-
                                TimeUnit.MINUTES.toSeconds((TimeUnit.MILLISECONDS.toMinutes(l))));
                timerTv.setText(sDuration);*/
        //scoreup = (TextView) findViewById(R.id.tvScorename);
        scoredown = (TextView) findViewById(R.id.tv_RealScore);
        scoreMessage = findViewById(R.id.scoreMsg);
        //dividerView = (View) findViewById(R.id.divider);

       /* attendedQ = findViewById(R.id.tvAttendedQuestions);
        notAttendedQ = findViewById(R.id.tvNotAttendedQuestions);
        correctAns = findViewById(R.id.tvCorrectAnswer);
        incorrectAns = findViewById(R.id.tvIncorrectAnswer);*/
        Intent getScore = getIntent();
        attendedQ= getScore.getStringExtra("AttendedCnt");
        notAttendedQ = getScore.getStringExtra("NotAttendedCnt");
        correctAns = getScore.getStringExtra("CorrectCnt");
        incorrectAns = getScore.getStringExtra("IncorrectCnt");
        scoredown.setText(getScore.getStringExtra("CorrectCnt") + "/" + getScore.getStringExtra("AttendedCnt")+" Score");

        scoreMessage.setText("You attempt "+attendedQ+" questions and from that "+correctAns+" answer is correct");
        //RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.0F);
               /* RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dynamicTextView = new TextView(getApplicationContext());
                dynamicTextView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                //dynamicTextView.setText(" Hello World ");
                addContentView(dynamicTextView,params);*/
    }
/*
            @Override
            public void onFinish() {
                //when finish hide the textview
                timerTv.setVisibility(View.GONE);
                //Display toast
                //dynamicTextView.setText(" Hello World ");
                scoreup.setVisibility(View.VISIBLE);
                dividerView.setVisibility(View.VISIBLE);
                scoredown.setVisibility(View.VISIBLE);
                attendedQ.setVisibility(View.VISIBLE);
                notAttendedQ.setVisibility(View.VISIBLE);
                correctAns.setVisibility(View.VISIBLE);
                incorrectAns.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(),"Countdown timer ended",Toast.LENGTH_LONG).show();
            }
        }.start();*/
        //}
}