package com.example.interview_puzzle_app;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.interview_puzzle_app.databinding.ActivityMainBinding;
import com.example.interview_puzzle_app.databinding.FragmentGalleryBinding;
import com.example.interview_puzzle_app.databinding.FragmentHistoryBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class History extends Fragment implements AdapterView.OnItemSelectedListener{

    private FragmentHistoryBinding binding;
    LinearLayout all_layout, company_layout;
    ImageView bottomSheet;
    TextView tvAll, tvCompanySort, tvOptionChosen;
    Spinner spin1;
    String selectedCompanyName;

    String[]  questionNumArr={"Q1","Q2","Q3","Q4","Q5","Q6","Q7","Q8","Q9"};
    String[] questionsArr = {"Question 1","Question 2","Question 3","Question 4","Question 5","Question 6","Question 7","Question 8","Question 9"};
    String[] compnayNamesArr = {"Choose the Company","Microsoft","Google","Apple","Meta","Netflix","Yahoo"};
    Activity context2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHistoryBinding.inflate(inflater, container, false);

        View root = binding.getRoot();
        getActivity().setTitle("History");

        bottomSheet = binding.btnClickme;
        tvOptionChosen = binding.optionSelected;
        spin1 = binding.companyNames;
        spin1.setOnItemSelectedListener(this);
        spin1.setSelection(1);
        context2 = getActivity();
        bottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();

            }
        });

        return root;
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(context2);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);
        tvAll = dialog.findViewById(R.id.tv_optAll);
        tvCompanySort = dialog.findViewById(R.id.tv_optCompany);
        all_layout = dialog.findViewById(R.id.linerLayoutAll);
        company_layout = dialog.findViewById(R.id.linearLayoutCompany);
        //tvChooseCompany = dialog.findViewById(R.id.companyChooser);
        tvAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spin1.setVisibility(View.INVISIBLE);
                ArrayList<QuestionHistory> questionHistoriesArrList1 = new ArrayList<>();
                for(int i = 0; i<questionNumArr.length; i++){
                    QuestionHistory q1 = new QuestionHistory(questionsArr[i],questionNumArr[i]);
                    questionHistoriesArrList1.add(q1);
                }
                enableItems(questionHistoriesArrList1);
                Toast.makeText(context2,"All is clicked",Toast.LENGTH_LONG).show();
                all_layout.setBackgroundResource(R.drawable.right_curve);
                tvOptionChosen.setText("All ");
                bottomSheet.setImageResource(R.drawable.all);
                dialog.hide();
            }
        });
        tvCompanySort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context2, "Company is clicked",Toast.LENGTH_LONG).show();

                ArrayAdapter a1 = new ArrayAdapter(context2, android.R.layout.simple_spinner_item,compnayNamesArr);
                a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spin1.setAdapter(a1);
                spin1.setVisibility(View.VISIBLE);
                dialog.hide();
                tvCompanySort.setSelected(true);
                all_layout.setBackgroundResource(R.drawable.dialog_bg);
                company_layout.setBackgroundResource(R.drawable.right_curve);
                tvOptionChosen.setText("Company ");
                bottomSheet.setImageResource(R.drawable.company);
            }
        });
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int i1;
        i1 = spin1.getSelectedItemPosition();
        selectedCompanyName =compnayNamesArr[i1];
        if(selectedCompanyName!="Choose the Company") {
            Toast.makeText(context2, "Selected: " + selectedCompanyName, Toast.LENGTH_LONG).show();

            ArrayList<QuestionHistory> questionHistoriesArrList2 = new ArrayList<>();
            for (int j = 0; j <= i1; j++) {
                QuestionHistory q1 = new QuestionHistory(questionsArr[j], questionNumArr[j]);
                questionHistoriesArrList2.add(q1);
            }
            enableItems(questionHistoriesArrList2);
        }
    }

    public void enableItems(ArrayList<QuestionHistory> questionHistoriesArrList){
        ListAdapter listAdapter = new ListAdapter(context2,questionHistoriesArrList);
        binding.listView1.setAdapter(listAdapter);
        binding.listView1.setClickable(true);
        binding.listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent i11 = new Intent(context2,ViewQuestionAttended.class);
                i11.putExtra("passQuestion",questionsArr[i]);
                startActivity(i11);
            }
        });
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}