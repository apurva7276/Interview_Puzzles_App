package com.example.interview_puzzle_app.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.interview_puzzle_app.HomePage;
import com.example.interview_puzzle_app.R;
import com.example.interview_puzzle_app.StartContest;
import com.example.interview_puzzle_app.StartContest;
import com.example.interview_puzzle_app.databinding.FragmentHomeBinding;
import com.example.interview_puzzle_app.ui.PracticeMode.QueList.QueModel;
import com.example.interview_puzzle_app.ui.QuestionUI.QuestionUiActivity;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HomeFragment extends Fragment{

    private FragmentHomeBinding binding;
    public TextView tvUname,tv1;
    public Button smartContestBtn, randomPuzzleBtn;
    Activity context;
    FirebaseFirestore firestore;
    QueModel model;
    ArrayList<QueModel> queModels;
    String noCompany ="Any";
    String noLevel = "All";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       // HomeViewModel homeViewModel =
         //       new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        tvUname = binding.userWelcome;

        smartContestBtn = binding.btnSmartContest;
        randomPuzzleBtn = binding.btnRandomPuzzle;
        tvUname.setText(tvUname.getText().toString()+"");
        context = getActivity();
        //tv1 = context.findViewById(R.id.tvUname);
        //tvUname.setText("Welcome "+tv1.getText().toString());
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int num = randomNum(1,30);
        firestore = FirebaseFirestore.getInstance();
        String randomQueID = "Que"+num;
        firestore.collection("Questions").document(randomQueID).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                try {
                model= value.toObject(QueModel.class);
                model.setQueID(randomQueID);
                queModels.add(model);
                }
               catch (Exception e){
                }
            }
        });
    }

    private int randomNum(int i, int i1) {
        int z = (int) (Math.random()*((i1-i)+1)+i); //Formula for random number generation within a range
        return z;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onStart()
    {
        super.onStart();
        randomPuzzleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Random clicked",Toast.LENGTH_SHORT).show();
                Intent i10 = new Intent(context, QuestionUiActivity.class);
                i10.putExtra("Question",model);
                i10.putExtra("NameBtn","Submit");
                i10.putExtra("QueList",queModels);
                startActivity(i10);
            }
        });
       smartContestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(context, StartContest.class);
                i1.putExtra("CompanyName",noCompany);
                i1.putExtra("LevelOfContest",noLevel);
                startActivity(i1);
            }
        });
    }
}