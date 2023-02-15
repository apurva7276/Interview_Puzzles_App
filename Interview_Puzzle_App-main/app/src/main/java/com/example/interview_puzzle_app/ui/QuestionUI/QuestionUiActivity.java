package com.example.interview_puzzle_app.ui.QuestionUI;

import static com.example.interview_puzzle_app.R.drawable.option_rounded_corner;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.interview_puzzle_app.HintActivity;
import com.example.interview_puzzle_app.QuestionActivity;
import com.example.interview_puzzle_app.R;
import com.example.interview_puzzle_app.databinding.ActivityPracticeQueListBinding;
import com.example.interview_puzzle_app.databinding.ActivityQuestionUiBinding;
import com.example.interview_puzzle_app.ui.PracticeMode.QueList.QueModel;
import com.example.interview_puzzle_app.db.SqliteDBHelper;
import com.example.interview_puzzle_app.db.UserData;

import java.util.ArrayList;

public class QuestionUiActivity extends AppCompatActivity {

    TextView COIN;
    ActivityQuestionUiBinding binding;
    public static int cntCorrectAns=0;
    public static int cntIncorrectAns=0;
    public static int cntAttendedQue=0;
    public static int cntNotAttendedQue=0;
    //Fetching Data from Local Database
    SqliteDBHelper DB;
    UserData userData;
    public static int cnt=1;
    QueModel que;
    ArrayList<QueModel> queModelArrayList;
    String videoLinkDb="";
    // queModelArrayList;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_ui);

        Intent i = getIntent();
        que = (QueModel)i.getSerializableExtra("Question");
        queModelArrayList = (ArrayList<QueModel>)i.getSerializableExtra("QueList");
        String btnname = i.getStringExtra("NameBtn");

        //Fetching Data
        DB = new SqliteDBHelper(getApplicationContext());
        DB.openDataBase();
        userData = DB.getUserInfo();

        binding = ActivityQuestionUiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.QATimerTV.setText("Solve");
        binding.QAQueNoTV.setText("Me");
        binding.QARightQueCountTV.setText("+"+cntCorrectAns);
        binding.QAWrongQueCountTV.setText("-"+cntIncorrectAns);
        binding.QAQueNoTV.setText(""+cnt+"/5");
        if(btnname.equals("Submit")){
            binding.QANextBtn.setText("Submit");
        }else {
            binding.QANextBtn.setText("Next");
        }

        binding.QANextBtn.setEnabled(false);
        binding.QAHinetBtn.setEnabled(false);

//        binding.QATimerTV.setBackgroundResource(R.drawable.option_rounded_corner);

        if(que.getLevel() < 2)
            binding.QATEarnedCoinsTV.setText("Easy");
        if(que.getLevel() >= 2 && que.getLevel() < 4)
            binding.QATEarnedCoinsTV.setText("Medium");
        if(que.getLevel() >= 4)
            binding.QATEarnedCoinsTV.setText("Hard");

        binding.QADetailQue.setText(que.getDetail());
        binding.QAoption1detail.setText(que.getOption1());
        binding.QAoption2detail.setText(que.getOption2());
        binding.QAoption3detail.setText(que.getOption3());
        binding.QAoption4detail.setText(que.getOption4());
        Glide.with(getApplicationContext())
                .load(que.getQueImg())
                .into(binding.QAQueImage);

        binding.QAOption1box.setOnClickListener(view -> {
            binding.QAOption1box.setClickable(false);
            binding.QAOption2box.setClickable(false);
            binding.QAOption3box.setClickable(false);
            binding.QAOption4box.setClickable(false);
            binding.QANextBtn.setEnabled(true);
            binding.QAHinetBtn.setEnabled(true);
            if(que.getOption1().equals(que.getCoption())){
                binding.QAOption1box.setBackgroundResource(R.drawable.correct_option);
                int currentCoins = userData.getCoins();
                int newCoins = currentCoins + 10;
                Boolean coinUpdataing = DB.updateCoins(userData.getId(), newCoins);
                if(coinUpdataing){
                    Toast.makeText(getApplicationContext(), "Correct Answer\n10 Coins Credited", Toast.LENGTH_SHORT).show();
                    Log.d("QuestionUI","Coins Updated");
                }
                else {
                    Log.d("QuestionUI","Coins Not Updated");
                }

                cntCorrectAns++;
                binding.QARightQueCountTV.setText("+"+cntCorrectAns);
            }
            else {
                binding.QAOption1box.setBackgroundResource(R.drawable.incorrect_option);
                int currentCoins = userData.getCoins();
                int newCoins = currentCoins - 7;
                Boolean coinUpdataing = DB.updateCoins(userData.getId(), newCoins);
                if(coinUpdataing){
                    Toast.makeText(getApplicationContext(), "Wrong Answer\n7 Coins deducted", Toast.LENGTH_SHORT).show();
                    Log.d("QuestionUI","Coins Updated");
                }
                else {
                    Log.d("QuestionUI","Coins Not Updated");
                }
                cntIncorrectAns++;
                binding.QAWrongQueCountTV.setText("-"+cntIncorrectAns);
            }
            cntAttendedQue++;
        });

        binding.QAOption2box.setOnClickListener(view -> {
            binding.QAOption1box.setClickable(false);
            binding.QAOption2box.setClickable(false);
            binding.QAOption3box.setClickable(false);
            binding.QAOption4box.setClickable(false);
            binding.QANextBtn.setEnabled(true);
            binding.QAHinetBtn.setEnabled(true);
            if(que.getOption2().equals(que.getCoption())){
                binding.QAOption2box.setBackgroundResource(R.drawable.correct_option);
                int currentCoins = userData.getCoins();
                int newCoins = currentCoins + 10;
                Boolean coinUpdataing = DB.updateCoins(userData.getId(), newCoins);
                if(coinUpdataing){
                    Toast.makeText(getApplicationContext(), "Correct Answer\n10 Coins Credited", Toast.LENGTH_SHORT).show();
                    Log.d("QuestionUI","Coins Updated");
                }
                else {
                    Log.d("QuestionUI","Coins Not Updated");
                }
                cntCorrectAns++;
                binding.QARightQueCountTV.setText("+"+cntCorrectAns);
            }
            else {
                binding.QAOption2box.setBackgroundResource(R.drawable.incorrect_option);
                int currentCoins = userData.getCoins();
                int newCoins = currentCoins - 7;
                Boolean coinUpdataing = DB.updateCoins(userData.getId(), newCoins);
                if(coinUpdataing){
                    Toast.makeText(getApplicationContext(), "Wrong Answer\n7 Coins deducted", Toast.LENGTH_SHORT).show();
                    Log.d("QuestionUI","Coins Updated");
                }
                else {
                    Log.d("QuestionUI","Coins Not Updated");
                }
                cntIncorrectAns++;
                binding.QAWrongQueCountTV.setText("-"+cntIncorrectAns);
            }
            cntAttendedQue++;
        });

        binding.QAOption3box.setOnClickListener(view -> {
            binding.QAOption1box.setClickable(false);
            binding.QAOption2box.setClickable(false);
            binding.QAOption3box.setClickable(false);
            binding.QAOption4box.setClickable(false);
            binding.QANextBtn.setEnabled(true);
            binding.QAHinetBtn.setEnabled(true);
            if(que.getOption3().equals(que.getCoption())){
                binding.QAOption3box.setBackgroundResource(R.drawable.correct_option);
                int currentCoins = userData.getCoins();
                int newCoins = currentCoins + 10;
                Boolean coinUpdataing = DB.updateCoins(userData.getId(), newCoins);
                if(coinUpdataing){
                    Toast.makeText(getApplicationContext(), "Correct Answer\n10 Coins Credited", Toast.LENGTH_SHORT).show();
                    Log.d("QuestionUI","Coins Updated");
                }
                else {
                    Log.d("QuestionUI","Coins Not Updated");
                }
                cntCorrectAns++;
                binding.QARightQueCountTV.setText("+"+cntCorrectAns);
            }
            else {
                binding.QAOption3box.setBackgroundResource(R.drawable.incorrect_option);
                int currentCoins = userData.getCoins();
                int newCoins = currentCoins - 7;
                Boolean coinUpdataing = DB.updateCoins(userData.getId(), newCoins);
                if(coinUpdataing){
                    Toast.makeText(getApplicationContext(), "Wrong Answer\n7 Coins deducted", Toast.LENGTH_SHORT).show();
                    Log.d("QuestionUI","Coins Updated");
                }
                else {
                    Log.d("QuestionUI","Coins Not Updated");
                }
                cntIncorrectAns++;
                binding.QAWrongQueCountTV.setText("-"+cntIncorrectAns);
            }
            cntAttendedQue++;
        });

        binding.QAOption4box.setOnClickListener(view -> {
            binding.QAOption1box.setClickable(false);
            binding.QAOption2box.setClickable(false);
            binding.QAOption3box.setClickable(false);
            binding.QAOption4box.setClickable(false);
            binding.QANextBtn.setEnabled(true);
            binding.QAHinetBtn.setEnabled(true);
            if(que.getOption4().equals(que.getCoption())){
                binding.QAOption4box.setBackgroundResource(R.drawable.correct_option);
                int currentCoins = userData.getCoins();
                int newCoins = currentCoins + 10;
                Boolean coinUpdataing = DB.updateCoins(userData.getId(), newCoins);
                if(coinUpdataing){
                    Toast.makeText(getApplicationContext(), "Correct Answer\n10 Coins Credited", Toast.LENGTH_SHORT).show();
                    Log.d("QuestionUI","Coins Updated");
                }
                else {
                    Log.d("QuestionUI","Coins Not Updated");
                }
                cntCorrectAns++;
                binding.QARightQueCountTV.setText("+"+cntCorrectAns);
            }
            else {
                binding.QAOption4box.setBackgroundResource(R.drawable.incorrect_option);
                int currentCoins = userData.getCoins();
                int newCoins = currentCoins - 7;
                Boolean coinUpdataing = DB.updateCoins(userData.getId(), newCoins);
                if(coinUpdataing){
                    Toast.makeText(getApplicationContext(), "Wrong Answer\n7 Coins deducted", Toast.LENGTH_SHORT).show();
                    Log.d("QuestionUI","Coins Updated");
                }
                else {
                    Log.d("QuestionUI","Coins Not Updated");
                }
                cntIncorrectAns++;
                binding.QAWrongQueCountTV.setText("-"+cntIncorrectAns);
            }
            cntAttendedQue++;
        });

        videoLinkDb = que.getLink();
        Log.d("Hint Video Link",""+videoLinkDb);
        binding.QAHinetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open new activity for youtube api
                Intent openYoutubeView = new Intent(QuestionUiActivity.this, HintActivity.class);
                openYoutubeView.putExtra("videoLink",videoLinkDb);
                startActivity(openYoutubeView);

            }
        });
        binding.QANextBtn.setOnClickListener(view -> {
            if(binding.QANextBtn.getText().toString().equals("Submit")){
                cntIncorrectAns=0;
                cntCorrectAns=0;
                cntAttendedQue=0;
                cntNotAttendedQue=0;
                finish();
            }
            else if (binding.QANextBtn.getText().toString().equals("Next"))
            {
               // cnt++;

                if(cnt<5) {
                    que = queModelArrayList.set(cnt, que);
                    Log.d("Size of list final", "Size:" + queModelArrayList.size());
                    Intent nextQuestion = new Intent(QuestionUiActivity.this, QuestionUiActivity.class);
                    nextQuestion.putExtra("Question", que);
                    nextQuestion.putExtra("NameBtn", "Next");
                    nextQuestion.putExtra("QueList", queModelArrayList);
                    startActivity(nextQuestion);
                    cnt++;
                    finish();
                }
                else
                {
                    cnt=0;

                    Intent displayScore = new Intent(QuestionUiActivity.this, QuestionActivity.class);
                    displayScore.putExtra("AttendedCnt",String.valueOf(cntAttendedQue));
                    displayScore.putExtra("NotAttendedCnt",String.valueOf(cntNotAttendedQue));
                    displayScore.putExtra("CorrectCnt",String.valueOf(cntCorrectAns));
                    displayScore.putExtra("IncorrectCnt",String.valueOf(cntIncorrectAns));
                    finish();
                    startActivity(displayScore);
                    cntIncorrectAns=0;
                    cntCorrectAns=0;
                    cntAttendedQue=0;
                    cntNotAttendedQue=0;

                }
                finish();
            }
            finish();
//            COIN = (TextView) findViewById(R.id.tvCoin);
//            userData = DB.getUserInfo();
//            COIN.setText(Integer.toString(userData.coins));

        });


    }
}