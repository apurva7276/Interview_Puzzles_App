package com.example.interview_puzzle_app.ui.PracticeMode.QueList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.interview_puzzle_app.R;
import com.example.interview_puzzle_app.databinding.ActivityPracticeQueListBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class PracticeQueListActivity extends AppCompatActivity {

    ActivityPracticeQueListBinding binding;
    ArrayList<QueModel> quetions;
    QueAdapter adapter;

    public static int TotQue=0;
    //FireStore
    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("28.04","Inside On create of que list");
        setContentView(R.layout.activity_practice_que_list);
        binding = ActivityPracticeQueListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.d("28.04","after binding of que list");

        Intent intent = getIntent();
        String companyNamefromCat = intent.getStringExtra("companyName");

        database = FirebaseFirestore.getInstance();
        quetions = new ArrayList<>();
        adapter = new QueAdapter(this, quetions);
        Log.d("28.04","after object creation of que list");

/*
        quetions.add(new QueModel(1,1,"Sum of Two Months","","","","","","","","","",true,true,true,true,true,true));
        quetions.add(new QueModel(1,1,"Sum of Three Months","","","","","","","","","",true,true,true,true,true,true));
*/

        Query query = database.collection("Questions");
        if(!companyNamefromCat.equals("ALL")) {
            query = database.collection("Questions").whereEqualTo(companyNamefromCat, true);
        }
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                TotQue=0;
                if (error != null) {
                    Log.d("28.04", "error at fire store " + error.toString());
                }
                quetions.clear();
                for (DocumentSnapshot snapshot : value) {
                    try {
                        QueModel model = snapshot.toObject(QueModel.class);
                        model.setQueID(snapshot.getId());
                        if(TotQue<5)
                        {
                            quetions.add(model);
                            TotQue++;
                        }

                    } catch (Exception e) {
                        Log.d("28.04", "Exception : " + e);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });

        binding.PQLqueList.setLayoutManager(new LinearLayoutManager(this));
        binding.PQLqueList.setAdapter(adapter);

    }

}