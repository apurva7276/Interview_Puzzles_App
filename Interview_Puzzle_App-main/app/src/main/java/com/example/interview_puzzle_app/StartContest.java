package com.example.interview_puzzle_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.interview_puzzle_app.ui.PracticeMode.QueList.QueModel;
import com.example.interview_puzzle_app.ui.QuestionUI.QuestionUiActivity;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class StartContest extends AppCompatActivity{

    public TextView question_tv, company_tv, level_tv;
    public Button continueBtn;
    String str_question, str_company, str_level, str_time;
    ArrayList<QueModel> queModels;
    FirebaseFirestore firestore;
    QueModel model;
    int level;
    public static int levelPerQuestion =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_contest);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Start Contest");

        question_tv = (TextView) findViewById(R.id.tv2NoQ);
        company_tv = (TextView) findViewById(R.id.tv2Company);
        level_tv = (TextView) findViewById(R.id.tv2Level);
        continueBtn = (Button) findViewById(R.id.btnContinue);

        Intent i9 = getIntent();
        str_company = i9.getStringExtra("CompanyName");
        str_level = i9.getStringExtra("LevelOfContest");

        company_tv.setText(str_company);
        level_tv.setText(str_level);
        if(str_level.equals("Easy")){
            level = 1;
        }
        else if(str_level.equals("Medium"))
        {
            level = 2;
        }
        else if(str_level.equals("Hard"))
        {
            level = 3;
        }
        else if(str_level.equals("All"))
        {
            level=1;
        }
        //hardcoded values which will be changed after database connectivity
        question_tv.setText("5");


        firestore = FirebaseFirestore.getInstance();
        queModels = new ArrayList<>();
        // queModels.add(new QueModel(1,"1","Sum of Two Months","abc","calender","august","sept","oct","nov","august","111","",true,true,true,true,true,true));

        //Log.d("data from arraylist1",queModels.toString());
        if(!str_level.equals("All")) {
            Query query = firestore.collection("Questions").whereEqualTo(str_company, true).whereEqualTo("level", level);
            query.addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    try {
                        for (DocumentSnapshot snapshot : value) {
                            model = snapshot.toObject(QueModel.class);
                            model.setQueID(snapshot.getId());
                            queModels.add(model);
                            continueBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    Intent i10 = new Intent(StartContest.this, QuestionUiActivity.class);
                                    i10.putExtra("Question", model);
                                    i10.putExtra("NameBtn", "Next");
                                    i10.putExtra("QueList", queModels);
                                    startActivity(i10);
                                }
                            });
                        }
                        Log.d("Size of list", "Size:" + queModels.size());
                    } catch (Exception e) {

                    }
                }
            });
        }
        else {
            Toast.makeText(StartContest.this,"All",Toast.LENGTH_SHORT).show();
            firestore.collection("Questions").whereEqualTo("level",1).addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    queModels.clear();
                    Log.d("Level init cnt:",""+queModels.size());
                    for(DocumentSnapshot snapshot: value){
                        try {
                            model = snapshot.toObject(QueModel.class);
                            model.setQueID(snapshot.getId());
                            if(queModels.size()>1)
                            {
                                Log.d("Level 1 cnt:",""+queModels.size());
                                break;
                            }
                            else {
                                queModels.add(model);
                            }

                        }catch (Exception e){

                        }
                    }
                }
            });
            firestore.collection("Questions").whereEqualTo("level",2).addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    for(DocumentSnapshot snapshot: value){
                        try {
                            model = snapshot.toObject(QueModel.class);
                            model.setQueID(snapshot.getId());
                            if(queModels.size()>3)
                            {
                                Log.d("Level 1 and 2 cnt:",""+queModels.size());
                                break;
                            }
                            else {
                                queModels.add(model);
                            }

                        }catch (Exception e){

                        }
                    }
                }
            });
            firestore.collection("Questions").whereEqualTo("level",3).addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    for(DocumentSnapshot snapshot: value){
                        try {
                            model = snapshot.toObject(QueModel.class);
                            model.setQueID(snapshot.getId());
                            if(queModels.size()>4)
                            {
                                Log.d("Level 1,2 and 3 cnt:",""+queModels.size());
                                break;
                            }
                            else {
                                queModels.add(model);
                            }

                        }catch (Exception e){

                        }
                    }
                }
            });
            continueBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i10 = new Intent(StartContest.this, QuestionUiActivity.class);
                    i10.putExtra("Question", model);
                    i10.putExtra("NameBtn", "Next");
                    i10.putExtra("QueList", queModels);
                    startActivity(i10);
                }
            });

        }

    }
}